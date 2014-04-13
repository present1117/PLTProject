#!/usr/bin/python

"""yaccing.py for BGD (Board Game Design)
"""
__teamNo__ = 7
__date__ = "Apr 12, 2014"

import sys
import ply.yacc as yacc
import lexing import BGDLexer

tokens = BGDLexer.tokens 

def getyacc():
    return yacc.yacc()

class Node(object):
    def __init__(self, type, children = [], act=None, token=None):
        self.type = type #Nonterminal + termianal
        self.children = children #list of children
        self.act = act #action at this node
        #self.token = token 

    ##developing...
    def __str__(self):
        return ""
    ##developing...
    def traverse(self, i):
        return ""


def p_input_stmt(t):
    'input_stmt: piece_stmt board_stmt player_tmt rule_stmt function_stmt'

def p_piece_stmt(t):
    'piece_stmt: PIECE : NEWLINE INDENT piece_expr DEDENT'

def p_piece_expr(t):
    '''piece_expr: STRING NUMBER NEWLINE
                 | piece_expr STRING NUMBER NEWLINE'''

def p_board_stmt(t):
    'board_stmt: BOARD : NEWLINE INDENT NUMBER NUMBER DEDENT'

def p_player_stmt(t):
    'player_stmt: PLAYER : NEWLINE INDENT NUMBER DEDENT'

def p_rule_stmt(t):
    'rule_stmt: RULE : NEWLINE INDENT action_stmt DEDENT'

def p_action_stmt(t):
    '''action_stmt: ACTION := NAME NEWLINE
                  | action_stmt ACTION := NAME NEWLINE'''

def p_function_stmt(t):
    '''function_stmt: funcdef
                    | function_stmt funcdef'''

def p_stmt(t):
    '''stmt: simple_stmt
           | compound_stmt'''

def p_simple_stmt(t):
    '''simple_stmt: assign_stmt
                  | flow_stmt'''

def p_compound_stmt(t): 
    '''compound_stmt: funcdef
                    | for_stmt
                    | if_stmt
                    | while_stmt'''

def p_assign_stmt(t):
    'assign_stmt: NAME := expr NEWLINE'
    t[0] = Node('assign_stmt',[p[1], p[3]], ':=')

def p_flow_stmt(t):
    '''flow_stmt: break_stmt
                | continue_stmt
                | return_stmt'''
    t[0] = Node('p_flow_stmt')

def p_funcdef(t):
    '''funcdef: DEF NAME parameters : suite
              | DEF NAME : suite'''

def p_parameters(t):
    '''parameters: parameters , parameter
                 | parameter'''

def p_expr(t):
    'expr: or_test'

def p_or_test(t):
    '''or_test: and_test
              | or_test OR and_test'''

def p_and_test(t):
    '''and_test: not_test
               | and_test AND not_test'''

def p_not_test(t):
    '''not_test: NOT not_test
               | comparison'''

def p_comparison(t):
    '''comparison: operand
                 | comparison = operand
                 | comparison > operand
                 | comparison < operand
                 | comparison >= operand
                 | comparison <= operand
                 | comparison ~= operand'''
    if len(t) == 2:
        t[0] = Node('comparison',[p[1]])
    else:
        t[0] = Node('comparison', [p[1],p[3]], p[2])

def p_operand(t):
    '''operand: term
              | operand + term
              | operand - term'''
    if len(t) == 2:
        t[0] = Node('operand', [p[1]])
    else:
        t[0] = Node('operand', [p[1], p[3]], p[2])

def p_term(t):
    '''term: factor
           |term * factor
           |term / factor
           |term % factor'''
    if len(t) == 2:
        t[0] = Node('term', [p[1]])
    else:
        t[0] = Node('term', [p[1], p[3]], p[2])

def p_power(t):
    '''power: atom
            | power trailer'''
    if len(t) == 2:
        t[0] = Node('power', [p[1]])
    else:
        t[0] = Node('power', [p[1], p[2]])

def p_atom(t):
    '''atom: NAME
           | STRING
           | NUMBER
           | BOOLEAN
           | NIL
           | func_expr
           | ( expr )'''
    if len(t) == 2:
        t[0] = Node('atom', [p[1]])
    else:
        t[0] = Node('atom', [p[2]])

def p_trailer(t):
    '''trailer: . NAME
              | [ expr ]'''
    if len(t) == 3:
        t[0] = Node('trailer', [p[2]], 'ATTR')
    else:
        t[0] = Node('trailer', [p[2]], 'INDEX')

def p_func_expr(t):
    '''func_expr: NAME ( )
                | NAME ( parameter_list )'''
    if len(t) == 4:
        t[0] = Node('func_expr', [p[1]])
    else:
        t[0] = Node('func_expr', [p[1], p[3]])

def p_for_stmt(t):
    '''for_stmt: FOR NAME = NUMBER TO NUMBER : suite
               | FOR NAME IN expr : suite'''

def p_if_stmt(t):
    '''if_stmt: IF expr : suite
              | IF expr : suite elseif_stmt
              | IF expr : suite ELSE : suite
              | IF expr : suite elseif_stmt ELSE : suite'''

def p_elseif_stmt(t):
    '''elseif_stmt: ELSEIF expr : suite
                  | elseif_stmt ELSEIF expr : suite'''

def p_while_stmt(t):
    'while_stmt: WHILE expr : suite'

def p_suite(t):
    '''suite: NEWLINE
            | NEWLINE INDENT suite_stmt DEDENT'''

def p_suite_stmt(t):
    '''suite_stmt: stmt
                 | suite_stmt stmt'''

def p_return_stmt(t):
    'return_stmt: RETURN expr NEWLINE'    
    t[0] = Node('return_stmt', [t[2]], 'RETURN')

def p_continue_stmt(t):
    'continue_stmt: CONTINUE NEWLINE'
    t[0] = Node('continue_stmt', [], 'CONTINUE')

def p_break_stmt(t):
    'break_stmt: BREAK NEWLINE'
    t[0] = Node('break_stmt', [], 'BREAK')






if __name__ == "__main__":
    testNode = Node('testtype', [Node('testtype2', [3], '1'),2], '0')
    print testNode.children[0].children
    print testNode.type
    print testNode.act
