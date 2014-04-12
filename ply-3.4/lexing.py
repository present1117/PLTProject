import ply.lex as lex

class BGDLexer(object):
    
    reserved = {
        'while' : 'WHILE',
        'for' : 'FOR',
        'if' : 'IF',
        'else' : 'ELSE',
        'elseif' : 'ELSEIF',
        'continue' : 'CONTINE',
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
        'NOT' : 'NOT'
        }
    tokens = [
        'NUMBER',
        'BOOLEAN',
        'STRING',
        'INDENT',
        'DEDENT',
        'ID',
        'COMMENT'
        ] + list(reserved.values())

    literals = [ '+', '-', '*', '/', '%', ':', '=', ',', '>', '<',\
                 '.', '(', ')', '[', ']']


    def t_NUMBER(self, t):
        r'[+-]?[0-9]+(\.[0-9]+)?'
        t.value = float(t.value)
        return t

    def t_BOOLEAN(self, t):
        r'YES|NO'
        if t.value == "YES":
            t.value = True
        else:
            t.value = False
        return t


    def t_INDENT(self, t):
        r'^[ \t]+'
        tabNum = t.value.count('\t')
        spaceNum = len(t.value) +   3*tabNum
        if spaceNum == 4*t.lexer.lastIndentLevel + 4:
            t.lexer.lastIndentLevel += 1
            return t

    def t_DEDENT(self, t):
        r'^[ \t]+'
        tabNum = t.value.count('\t')
        spaceNum = len(t.value) + 3*tabNum
        if spaceNum == 4*t.lexer.lastIndentLevel - 4:
            t.lexer.lastIndentLevel += 1
            return t

    def t_STRING(self, t):
        r'\'.*\''
        t.value = t.value[1:-1]
        return t

    def t_ID(self, t):
        r'[a-zA-Z_][a-zA-Z_0-9]*'
        t.type = self.reserved.get(t.value,'ID')
        return t

    t_ignore_COMMENT = r'@@.*$'
           

    def t_error(self, t):
        print "Illegal character '%s'" % t.value[0]
        t.lexer.skip(1)

    def build(self,**kwargs): 
        self.lexer = lex.lex(module=self, **kwargs)
        self.lexer.lastIndentLevel = 0

    def tok_str(self, data):
        self.lexer.input(data)
        tok_str = ""
        while True:
            tok = self.lexer.token()
            if not tok:
                break
            tok_str += str(tok) + "\n"
        return tok_str

if __name__ == '__main__':
    lexer = BGDLexer()
    lexer.build()
    
    print 'Input a text'
    line = raw_input()
    lexer.tok_str(line)
