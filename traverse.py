#!/usr/bin/python

""" traverse AST (abstract syntax tree). 
"""
__teamNo__ = 7
__date__ = "Apr 12, 2014"

import sys
from collections import defaultdict
from lexing import BGDLexer
from yaccing import *
import ply.lex as lex
#import re


builtInFunc = ['isEmpty', 'numberInRow']
actionFunc = ['add', 'move', 'win', 'remove']
funcParam = defaultdict(dict)
funcParam['add']['returnValue'] = 'boolean'
funcParam['add']['param'] = ['int[]']
funcParam['win']['returnValue'] = 'boolean'
funcParam['win']['param'] = ['int[]']
funcParam['remove']['returnValue'] = 'boolean'
funcParam['remove']['param'] = ['int[]']
funcParam['move']['returnValue'] = 'boolean'
funcParam['move']['param'] = ['int[]', 'int[]']
funcParam['isEmpty']['returnValue'] = 'boolean'
funcParam['isEmpty']['param'] = ['int[]']
funcParam['numberInRow']['returnValue'] = 'int'
funcParam['numberInRow']['param'] = ['int[]']
#funcParam['add'] = ['boolean', 'int[]']
#funcParam['win'] = ['boolean', 'int[]']
#funcParam['move'] = ['boolean', 'int[]', 'int[]']
#funcParam['remove'] = ['boolean', 'int[]']

localFunc = []
currentParam = {}
currentParam['returnValue'] = 'void'
currentParam['param'] = {}


class Traverse(object):
    def __init__(self, tree, output = sys.stdout): 
        self.output = output
        self.root = tree

    def traverse(self, node):
        if isinstance(node, Node):
            for subtree in node.children:
                self.traverse(subtree)
            self.visit(node)
    
    def visit(self, node):
        if node.leaf:
            self.take_action(node)
        else:
            self.pass_value(node)
    
    def take_action(self, node):
        if node.leaf == ':=':
            node.string = node.children[0] + '=' + node.children[1].string + ';\n'
            #if node.string[-1] != '\n':
            #    node.string += ';\n'
            node.token = node.children[1].token
            if node.children[0] not in currentParam['param']:
                currentParam['param'][node.children[0]] = node.token
                node.string = node.token + ' ' + node.string
        #elif node.leaf == 'assigned':
            ##function default parameters assignment
        #    node.string = node.children[0].string + '=' + node.children[1].string
        elif node.leaf == '*' or node.leaf == '/' or node.leaf == '%':
            node.string = node.children[0].string + node.leaf + node.children[1].string
            node.token = node.children[0].token
        elif node.leaf == 'or':
            node.string = node.children[0].string + '||' + node.children[1].string
            node.token = 'boolean'
        elif node.leaf == 'and':
            node.string = node.children[0].string + '&&' + node.children[1].string
            node.token = 'boolean'
        elif node.leaf == 'not':
            node.string = '!' + node.children[0].string
            node.token = 'boolean'
        elif node.leaf == '=':
            node.string = node.children[0].string + '==' + node.children[1].string
            node.token = 'boolean'
        elif node.leaf == '>':
            node.string = node.children[0].string + '>' + node.children[1].string
            node.token = 'boolean'        
        elif node.leaf == '<':
            node.string = node.children[0].string + '<' + node.children[1].string
            node.token = 'boolean'
        elif node.leaf == '>=':
            node.string = node.children[0].string + '>=' + node.children[1].string        
            node.token = 'boolean'
        elif node.leaf == '<=':
            node.string = node.children[0].string + '<=' + node.children[1].string        
            node.token = 'boolean'
        elif node.leaf == '~=':
            node.string = node.children[0].string + '!=' + node.children[1].string
            node.token = 'boolean'
        elif node.leaf == 'ATTR':
            node.string = '.' + node.children[0].string
        elif node.leaf == 'INDEX':
            node.string = '[' + node.children[0].string + ']'
        elif node.leaf == 'ELSEIF' or node.leaf == 'ELSE':
            node.string = self.gen_if_stmt(node)
        elif node.leaf == 'emptyParam':
            node.string = self.gen_funcdef(node)
        else: 
            print "Error in take_value()\n"


    def pass_value(self, node):
        if node.type == 'break_stmt':
            node.string = 'break;\n'
        elif node.type == 'continue_stmt':
            node.string = 'continue;\n'
        elif node.type == 'return_stmt':
            node.string = 'return ' + node.children[0].string +';\n'
            node.token = node.children[0].token
            currentParam['returnValue'] = node.token
        elif node.type == 'suite_stmt':
            if len(node.children) ==1:
                node.string = node.children[0].string
            else:
                node.string = node.children[0].string + '\n' + node.children[1].string
        elif node.type == 'suite':
            node.string = '{\n' + node.children[0].string + '}\n'
        elif node.type == 'while_stmt':
            node.string = self.gen_while_stmt(node)
        elif node.type == 'if_stmt':
            node.string = self.gen_if_stmt(node)
        elif node.type == 'elseif_stmt':
            node.string = self.gen_elseif_stmt(node)
        elif node.type == 'for_stmt':
            node.string = self.gen_for_stmt(node)
        elif node.type == 'parameter_list':
            if len(node.children) == 1:
                node.string = node.children[0].string
            else:
                node.string = node.children[0].string +', '+node.children[1].string
        elif node.type == 'func_expr':
            node.string = self.gen_func_expr(node)
            node.token = funcParam[node.children[0]]['returnValue']
            vars = node.children[1].string.split(', ')
            for i in range(0,len(vars)):
                if vars[i] not in currentParam['param']:
                    currentParam['param'][vars[i]] = funcParam[node.children[0]]['param'][i]
        elif node.type == 'atom':
            if isinstance(node.children[0], Node):
                node.string = node.children[0].string
            else:
                node.string = node.children[0]
                if node.string == 'YES': node.string = 'true'
                elif node.string == 'NO': node.string = 'false'
                elif node.string == 'NIL': node.string = 'null'
                elif node.token == 'String': node.string = '\"' + node.string[1:-1] + '\"'
        elif node.type == 'position':
            node.string = '{' + node.children[0].string + ', ' + node.children[1].string + '}'
        elif node.type == 'array':
            node.string = '{' + node.children[0].string + '}'
            #print node.string, node.children[0].type, node.children[0].string
            node.token = node.children[0].token + '[]'
        elif node.type == 'power':
            if len(node.children) == 1:
                node.string = node.children[0].string
            else:
                node.string = node.children[0].string + node.children[1].string
        elif node.type == 'parameters':
            if len(node.children) == 1:
                node.string = [node.children[0].string]
            else:
                node.string = list(node.children[0].string.append(node.children[1].string))
        elif node.type == 'funcdef':
            node.string = self.gen_funcdef(node)
        elif node.type == 'function_stmt':
            node.string = self.gen_function_stmt(node)
        elif node.type == 'action_stmt':
            node.string = self.gen_action_stmt(node)
        elif node.type == 'rule_stmt':
            node.string = self.gen_rule_stmt(node)
        elif node.type == 'player_stmt':
            node.string = self.gen_player_stmt(node)
        elif node.type == 'board_stmt':
            node.string = self.gen_board_stmt(node)
        elif node.type == 'piece_expr':
            node.string = self.gen_piece_expr(node)
        elif node.type == 'piece_stmt':
            node.string = self.gen_piece_stmt(node)
        elif node.type == 'input_stmt':
            node.string = self.gen_input_stmt(node)
        elif node.type == 'factor' or node.type == 'term' or node.type == 'operand' or node.type == 'comparison' or node.type == 'not_test' or node.type == 'and_test' or node.type == 'or_test' or node.type == 'expr' or node.type == 'parameter' or node.type == 'flow_stmt' or node.type == 'compound_stmt' or node.type == 'simple_stmt' or node.type == 'stmt':
            if isinstance(node.children[0], Node):
                node.string = node.children[0].string
            else:
                node.string = node.children[0]
            

        else:
            print node.type
            print "Error in pass_value()"

        if isinstance(node.children[0], Node):
            if node.token == None: node.token = node.children[0].token


    def gen_action_stmt(self, node):
        if len(node.children) == 1:
            localFunc.append(node.children[0])
        else:
            localFunc.append(node.children[1])
        return ''

    def gen_rule_stmt(self, node):
        if 'win' not in localFunc: print "Action win is not declared.\n"
        if 'add' not in localFunc and 'move' not in localFunc: "At least either action add or move should be declared in RULE. \n"
        return ''

    def gen_player_stmt(self, node):
        return 'static int playerNum = ' + node.children[0] + ';\n'

    def gen_board_stmt(self, node):
        return 'static int boardRow = ' + node.children[0] + ';\n' + 'static int boardCol = ' + node.children[1] + ';\n'

    def gen_piece_stmt(self, node):
        s1 = 'enum pieceType{'
        s2 = 'static int [] pieceNum = {'
        for item in node.children[0].string:
            s1 = s1 + item[0].upper() + ','
            s2 = s2 + item[1] + ','
        s1 = s1[0:-1] + '};\n'
        s2 = s2[0:-1] + '};\n'
        return s1 + s2

    def gen_piece_expr(self, node):
        if len(node.children) == 1:
            return [[node.children[0], '0']]
        elif len(node.children) == 2:
            return [[node.children[0], node.children[1]]]
        else:
            return list(node.children[0].string.append([node.children[1], node.children[2]]))

    def gen_input_stmt(self, node):
        s = 'package edu.columbia.PLT.BGD;\nimport java.lang.String;\nimport java.util.*;\npublic class GameDesigner{\n'
        s += node.children[0].string + node.children[1].string + node.children[2].string + node.children[4].string
        for func in actionFunc:
            if func not in localFunc:
                s += 'public static ' + funcParam[func]['returnValue'] + ' ' + func + '_res('
                para_list = funcParam[func]['param']
                if len(funcParam[func]) > 0:
                    for i in range(0,len(para_list)):
                        s = s + para_list[i] + ' ' + 'par' + str(i) + ','
                s = s[0:-1]
                s += ')\n'
                s += '{\nreturn true;\n}\n'
        s += '}\n'
        return s
    
    def gen_while_stmt(self, node):
        return 'while(' + node.children[0].string + ')\n'\
            + node.children[1].string
    
    def gen_elseif_stmt(self, node):
        if len(node.children) == 2:
            return 'else if(' + node.children[0].string + ')'\
                   +node.children[1].string
        else:
            return node.children[0].string\
                   + 'else if(' + node.children[1].string + '){\n'\
                   + node.children[2].string + '}\n'

    def gen_if_stmt(self, node):
        s = 'if(' + node.children[0].string + ')\n' + node.children[1].string
        if len(node.children) == 4:
            s = s + node.children[2].string + 'else\n' + node.children[3].string
        elif len(node.children) == 3:
            if node.leaf == 'ELSE':
                s = s + 'else\n' + node.children[2].string
            else:
                s = s + node.children[2].string
        return s

    def gen_for_stmt(self, node):
        if len(node.children) == 4:
            idx = node.children[0]
            s = 'for( int ' + idx +'='+node.children[1]+';' + idx+ '<='+node.children[2]\
                + ';'+idx+'++)' + node.children[3].string
        else:
            array_type = node.children[1].token[0:-2]
            s = 'for(' + array_type +' '+ node.children[0] + ':'+\
                node.children[1].string + ')\n'+ node.children[2].string
        return s

    def gen_function_stmt(self, node):
        if len(node.children) == 1:
            return node.children[0].string
        else:
            return node.children[0].string + '\n' + node.children[1].string

    
    def gen_funcdef(self, node):
        s = ''
        if len(node.children) == 2:
            s = 'public static ' + currentParam['returnValue']  + ' ' + node.children[0] + '()\n'
            funcParam[node.children[0]]['returnValue'] = currentParam['returnValue']
            funcParam[node.children[0]]['param'] = []
            currentParam['param'] = {}
            currentParam['returnValue'] = 'void'
            return s + node.children[1].string
        elif len(node.children) == 4:
            s = node.children[0].string
            children = list(node.children[1:len(node.children)])
        if node.leaf:
            s = s + 'public static ' + currentParam['returnValue'] + ' ' + node.children[1] + '()\n'
            funcParam[node.children[1]]['returnValue'] = currentParam['returnValue']
            funcParam[node.children[1]]['param'] = []
            currentParam['param'] = {}
            currentParam['returnValue'] = 'void'
            return s + node.children[2].string
        else:
            if len(node.children) == 3:
                children = list(node.children)
            s += 'public static '
            if children[0] in actionFunc:
                para_list = funcParam[children[0]]['param']
                s = s + funcParam[children[0]]['returnValue'] + ' ' + children[0] + '_res' + ' ('
                if len(funcParam[children[0]]) > 0:
                    for i in range(0,len(para_list)):
                        s = s + para_list[i] + ' ' + children[1].string[i-1] + ','
                s = s[0:-1]
                s += ')\n'
            else:
                para_dict = currentParam['param']
                s = s + currentParam['returnValue'] + ' ' + children[0] + ' ('
                for param in children[1].string:
                    s = s + para_dict[param] + ' ' + param + ','
                    funcParam[children[0]]['param'].append(para_dict[param])
                s = s[0:-1]
                s += ')\n'
                funcParam[children[0]]['returnValue'] = currentParam['returnValue']
            currentParam['returnValue'] = 'void'
            currentParam['param'] = {}
            return s + children[-1].string

    def gen_func_expr(self, node):
        if node.children[0] in builtInFunc:
            s = 'Functions.' + node.children[0]
        else:
            s = node.children[0]
        if len(node.children) == 1:
            return s + '()'
        else:
            return s + '(' + node.children[1].string + ')'

    def getJava(self):
        self.traverse(self.root)
        return self.root.string




if __name__ == "__main__":
    m = BGDLexer()
    inputFile = open(sys.argv[1])
    #inputFile = open('tic-tac-toe.bgd')
    inputData = inputFile.read()
    #print inputData
    m.input(inputData)
    parser = yacc.yacc()
    result = parser.parse(tokenfunc = m.token)
    code = Traverse(result).getJava()
    #print code
    outputFile = open('GameDesigner.java','w')
    outputFile.write(code)
    inputFile.close()
    outputFile.close()
