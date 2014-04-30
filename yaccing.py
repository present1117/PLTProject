#!/usr/bin/python

"""yaccing.py for BGD (Board Game Design)
"""
__teamNo__ = 7
__date__ = "Apr 12, 2014"

import sys
import ply.yacc as yacc
import lexing

tokens = lexing.BGDLexer.tokens 

def getyacc():
    return yacc.yacc()

class Node(object):
    def __init__(self, type, children = [], leaf=None, string = None):
        self.type = type #Nonterminal + termianal
        self.children = children #list of children
        self.leaf = leaf #action at this node
        self.string = string #value of attributes


start = 'input_stmt'

def p_input_stmt(t):
    'input_stmt : piece_stmt board_stmt player_stmt rule_stmt function_stmt'
    t[0] = Node('input_stmt', [t[1], t[2], t[3], t[4], t[5]])

def p_piece_stmt(t):
    'piece_stmt : PIECE ":" NEWLINE INDENT piece_expr DEDENT'
    t[0] = Node('piece_stmt', [t[5]])

def p_piece_expr(t):
    '''piece_expr : STRING NUMBER NEWLINE
                 | STRING NEWLINE
                 | piece_expr STRING NUMBER NEWLINE'''
    if len(t) == 3:
        t[0] = Node('piece_expr', [t[1][1:-1]])
    elif len(t) == 4:
        t[0] = Node('piece_expr', [t[1][1:-1], t[2]])
    else:
        t[0] = Node('piece_expr', [t[1], t[2][1:-1], t[3]])

def p_board_stmt(t):
    'board_stmt : BOARD ":" NEWLINE INDENT NUMBER NUMBER NEWLINE DEDENT'
    t[0] = Node('board_stmt', [t[5], t[6]])

def p_player_stmt(t):
    'player_stmt : PLAYER ":" NEWLINE INDENT NUMBER NEWLINE DEDENT'
    t[0] = Node('player_stmt', [t[5]])

def p_rule_stmt(t):
    'rule_stmt : RULE ":" NEWLINE INDENT action_stmt DEDENT'
    t[0] = Node('rule_stmt', [t[5]])

def p_action_stmt(t):
    '''action_stmt : ACTION ":" "=" ID NEWLINE
                  | action_stmt ACTION ":" "=" ID NEWLINE'''
    if len(t) == 6:
        t[0] = Node('action_stmt', [t[4]])
    else:
        t[0] = Node('action_stmt', [t[1], t[5]])

def p_function_stmt(t):
    '''function_stmt : FUNCTION ":" NEWLINE INDENT funcdef DEDENT
                    | function_stmt funcdef'''
    if len(t) == 3:
        t[0] = Node('function_stmt', [t[1], t[2]])
    else:
        t[0] = Node('function_stmt', [t[5]])

def p_stmt(t):
    '''stmt : simple_stmt
           | compound_stmt'''
    t[0] = Node('stmt', [t[1]])

def p_simple_stmt(t):
    '''simple_stmt : assign_stmt
                  | flow_stmt'''
    t[0] = Node('simple_stmt', [t[1]])

def p_compound_stmt(t): 
    '''compound_stmt : funcdef
                    | for_stmt
                    | if_stmt
                    | while_stmt'''
    t[0] = Node('compound_stmt', [t[1]])

def p_assign_stmt(t):
    'assign_stmt : ID ":" "=" expr NEWLINE'
    t[0] = Node('assign_stmt',[t[1], t[4]], ':=')

def p_flow_stmt(t):
    '''flow_stmt : break_stmt
                | continue_stmt
                | return_stmt'''
    t[0] = Node('flow_stmt', [t[1]])

def p_funcdef(t):
    '''funcdef : DEF ID parameters ":" suite
              | DEF ID ":" suite
              | funcdef DEF ID parameters ":" suite
              | funcdef DEF ID ":" suite'''
    #print 'In funcdef'
    if len(t) == 6 and t[1] == 'def':
        t[0] = Node('funcdef', [t[2], t[3], t[5]])
    elif len(t) == 6:
        t[0] = Node('funcdef', [t[1], t[3], t[5]], 'emptyParam')
    elif len(t) == 5:
        t[0] = Node('funcdef', [t[2], t[4]])
    else:
        t[0] = Node('funcdef', [t[1], t[3], t[4], t[6]])
        

def p_parameters(t):
    '''parameters : parameters "," parameter
                 | parameter'''
    if len(t) == 4:
        t[0] = Node('parameters', [t[1], t[3]])
    else:
        t[0] = Node('parameters', [t[1]])

def p_parameter(t):
    'parameter : ID '
    if len(t) == 2:
        t[0] = Node('parameter', [t[1]])
    #else:
 #       t[0] = Node('parameter', [t[1], t[4]], 'assigned')

def p_expr(t):
    'expr : or_test'
    t[0] = Node('expr', [t[1]])

def p_or_test(t):
    '''or_test : and_test
              | or_test OR and_test'''
    if len(t) == 2:
        t[0] = Node('or_test', [t[1]])
    else:
        t[0] = Node('or_test', [t[1], t[3]], 'or')

def p_and_test(t):
    '''and_test : not_test
               | and_test AND not_test'''
    if len(t) == 2:
        t[0] = Node('and_test', [t[1]])
    else:
        t[0] = Node('and_test', [t[1], t[3]], 'and')

def p_not_test(t):
    '''not_test : NOT not_test
               | comparison'''
    if len(t) == 3:
        t[0] = Node('not_test', [t[2]], 'not')
    else:
        t[0] = Node('not_test', [t[1]])

def p_comparison(t):
    '''comparison : operand
                 | comparison "=" operand
                 | comparison ">" operand
                 | comparison "<" operand
                 | comparison ">" "=" operand
                 | comparison "<" "=" operand
                 | comparison "~" "=" operand'''
    if len(t) == 2:
        t[0] = Node('comparison',[t[1]])
    elif len(t) == 4:
        t[0] = Node('comparison', [t[1],t[3]], t[2])
    else:
        t[0] = Node('comparison', [t[1], t[4]], t[2]+t[3])

def p_operand(t):
    '''operand : term
              | operand "+" term
              | operand "-" term'''
    if len(t) == 2:
        t[0] = Node('operand', [t[1]])
    else:
        t[0] = Node('operand', [t[1], t[3]], t[2])

def p_term(t):
    '''term : factor
           | term "*" factor
           | term "/" factor
           | term "%" factor'''
    if len(t) == 2:
        t[0] = Node('term', [t[1]])
    else:
        t[0] = Node('term', [t[1], t[3]], t[2])

def p_factor(t):
    '''factor : power
            | "+" factor
            | "-" factor'''
    if len(t) == 2:
        t[0] = Node('factor', [t[1]])
    elif t[1] == '+':
        t[0] = Node('factor', [t[2]], 'uadd')
    elif t[1] == '-':
        t[0] = Node('factor', [t[2]], 'uminus')

def p_power(t):
    '''power : atom
            | power trailer'''
    if len(t) == 2:
        t[0] = Node('power', [t[1]])
    else:
        t[0] = Node('power', [t[1], t[2]])

def p_atom(t):
    '''atom : ID
           | STRING
           | NUMBER
           | BOOLEAN
           | NIL
           | func_expr
           | "(" expr ")" '''
    if len(t) == 2:
        t[0] = Node('atom', [t[1]])
    else:
        t[0] = Node('atom', [t[2]])
    

def p_trailer(t):
    '''trailer : "." ID
              | "[" expr "]" '''
    if len(t) == 3:
        t[0] = Node('trailer', [t[2]], 'ATTR')
    else:
        t[0] = Node('trailer', [t[2]], 'INDEX')

def p_func_expr(t):
    '''func_expr : ID "(" ")"
                | ID "(" parameter_list ")" '''
    if len(t) == 4:
        t[0] = Node('func_expr', [t[1]])
    else:
        t[0] = Node('func_expr', [t[1], t[3]])

def p_parameter_list(t):
    '''parameter_list : expr
                    | parameter_list "," expr'''
    if len(t) == 2:
        t[0] = Node('parameter_list', [t[1]])
    else:
        t[0] = Node('parameter_list', [t[1], t[3]])

def p_for_stmt(t):
    '''for_stmt : FOR ID "=" NUMBER TO NUMBER ":" suite
               | FOR ID IN expr ":" suite'''
    if len(t) == 10:
        t[0] = Node('for_stmt', [t[2], t[4], t[6], t[8]])
    else:
        t[0] = Node('for_stmt', [t[2], t[4], t[6]])

def p_if_stmt(t):
    '''if_stmt : IF expr ":" suite
              | IF expr ":" suite elseif_stmt
              | IF expr ":" suite ELSE ":" suite
              | IF expr ":" suite elseif_stmt ELSE ":" suite'''
    #print 'In if'
    if len(t) == 5:
        t[0] = Node('if_stmt', [t[2], t[4]])
    elif len(t) == 6:
        t[0] = Node('if_stmt', [t[2], t[4], t[5]], 'ELSEIF')
    elif len(t) == 8:
        t[0] = Node('if_stmt', [t[2], t[4], t[7]], 'ELSE')
    elif len(t) == 9:
        t[0] = Node('if_stmt', [t[2], t[4], t[5], t[8]])

def p_elseif_stmt(t):
    '''elseif_stmt : ELSEIF expr ":" suite
                  | elseif_stmt ELSEIF expr ":" suite'''
    if len(t) == 6:
        t[0] = Node('elseif_stmt', [t[2], t[4]])
    else:
        t[0] = Node('elseif_stmt', [t[1], t[3], t[5]])

def p_while_stmt(t):
    'while_stmt : WHILE expr ":" suite'
    t[0] = Node('while_stmt', [t[1], t[2]])

def p_suite(t):
    '''suite : NEWLINE
            | NEWLINE INDENT suite_stmt DEDENT'''
    if len(t) == 2:
        t[0] = Node('suite')
    else:
        t[0] = Node('suite', [t[3]])

def p_suite_stmt(t):
    '''suite_stmt : stmt
                 | suite_stmt stmt'''
    if len(t) == 2:
        #print 'In suite'
        t[0] = Node('suite_stmt', [t[1]])
    else:
        t[0] = Node('suite_stmt', [t[1], t[2]])

def p_return_stmt(t):
    'return_stmt : RETURN expr NEWLINE'
    #print 'In return'
    t[0] = Node('return_stmt', [t[2]])

def p_continue_stmt(t):
    'continue_stmt : CONTINUE NEWLINE'
    t[0] = Node('continue_stmt')

def p_break_stmt(t):
    'break_stmt : BREAK NEWLINE'
    t[0] = Node('break_stmt')


def p_error(p):
    print "Syntax error in input!"
    

if __name__ == "__main__":
    m = lexing.BGDLexer()
    #parser = yacc.yacc(debug = True)
    parser = yacc.yacc()
    f = open('tic-tac-toe.bg')
    line = f.read()
    #print line
    m.input(line)
    parser.parse(tokenfunc = m.token)
