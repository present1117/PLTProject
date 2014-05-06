import ply.lex as lex
import copy

class BGDLexer(object):
    
    reserved = {
        'while' : 'WHILE',
        'for' : 'FOR',
        'if' : 'IF',
        'else' : 'ELSE',
        'elseif' : 'ELSEIF',
        'continue' : 'CONTINUE',
        'return' : 'RETURN',
        'break' : 'BREAK',
        'NIL' : 'NIL',
        'PIECE' : 'PIECE',
        'BOARD' : 'BOARD',
        'PLAYER' : 'PLAYER',
        'RULE' : 'RULE',
        'FUNCTION' : 'FUNCTION',
        'action' : 'ACTION',
        'def' : 'DEF',
        'OR' : 'OR',
        'AND' : 'AND',
        'NOT' : 'NOT',
        'to' : 'TO',
        'in' : 'IN',
        'global' : 'GLOBAL'
        }
    tokens = [
        #'EMPTYLINE',
        'NUMBER',
        'BOOLEAN',
        'STRING',
        'EMPTYLINE',
        'INDENT',
        'DEDENT',
        'ID',
        'COMMENT',
        'NEWLINE',
        
        'WHITESPACE'
        
        ] + list(reserved.values())

    literals = [ '+', '-', '*', '/', '%', ':', '~', '=', ',', '>', '<',
                 '.', '(', ')', '[', ']']


    def t_NUMBER(self, t):
        r'[+-]?[0-9]+(\.[0-9]+)?'
        
        return t

    def t_BOOLEAN(self, t):
        r'YES|NO'
        return t


    def t_STRING(self, t):
        r'\'.*?\''
        return t

    def t_ID(self, t):
        r'[A-Za-z_][A-Za-z0-9_]*'
        t.type = self.reserved.get(t.value,'ID')
        return t

    def t_NEWLINE(self, t):
      r'\n'
      t.lexer.lineno += 1
      return t

    t_ignore_COMMENT = r'@@.*'
    
    t_WHITESPACE = r'[\ \t]'

    
    def t_error(self, t):
        print "Illegal character '%s'" % t.value[0]
        t.lexer.skip(1)
        
    ##########
    ## ALL RE PART END
    ##########
    def __init__(self, reportError = None, debug=0, optimize=0, lextab='lextab', reflags=0): 
        self.lexer = lex.lex(debug=debug, optimize=optimize, lextab=lextab, reflags=reflags, module=self)
        self.token_stream = None
        self.reportError = reportError

        self.currentLineIndent = 0
        self.indentLevel = [0]

    def input(self, s):
        # add newline,
        # because the Filter-Method expects a newline at the end of a file 
        s = s + "\n"
        self.lexer.paren_count = 0
        self.lexer.input(s)
        self.token_stream = self._filter( )
        self.inputString = s

    def token(self):
        
        try:
            t = next(self.token_stream)
            return t
        except StopIteration:
            return None
    
    def _filter(self):
      tokens = iter(self.lexer.token, None)
      indentSwitch = True
      curIndent = 0
      indentStack = [0]
      newlinePos = 0
      
      for t in tokens:
         t.lexer = self
         if indentSwitch:
            
            if t.type == "WHITESPACE":
               if t.value == ' ':
                    curIndent += 1
               else:
                    curIndent += 4
            elif t.type == "NEWLINE":
               newlinePos = t.lexpos
               curIndent = 0
            else:
               while curIndent < indentStack[-1]: 
                  indentStack.pop()
                  yield self._new_token("DEDENT", t.lineno, newlinePos + 1, self, "DEDENT")
               if curIndent > indentStack[-1]:
                  indentStack.append(curIndent)
                  yield self._new_token("INDENT", t.lineno, newlinePos + 1, self,  "INDENT")
               yield t
               indentSwitch = False     
         else:
            if t.type == "WHITESPACE": pass
            elif t.type == "NEWLINE":
               curIndent = 0
               newlinePos = t.lexpos
               indentSwitch = True
               yield t
            else: yield t
      while 0 < indentStack[-1]:
         indentStack.pop()
         yield self._new_token("DEDENT", t.lineno, t.lexpos, self, "DEDENT")

   # Generate a Token
    def _new_token(self, type, lineno, lexpos, lexer, value=None):
        tok = lex.LexToken()
        tok.lexer = lexer
        tok.type = type
        tok.lineno = lineno
        tok.lexpos = lexpos
        if value == None:
            tok.value = type
        else:
            tok.value = value
        return tok

    def tok_str(self, data):
        tok_str = ""
        while True:
            tok = self.token()
            if not tok:
                break
            tok_str += str(tok) + "\n"
        print tok_str
        return tok_str
    
if __name__ == '__main__':
    m = BGDLexer()
    #var = raw_input("Please enter something: ")
    f = open('tic-tac-toe.bgd')
    line = f.read()
    m.input(line)
    m.tok_str(line)
