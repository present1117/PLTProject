import sys
import unittest
sys.path.append('..')
from lexing import BGDLexer
from yaccing import *
from traverse import *
import ply.lex as lex
import re
from test_cases import BGDTests

class testTraverse(unittest.TestCase):
    def setUp(self):
        self.m = BGDLexer()
        f = open('test_traverse_result', 'w')

    def test_piece_stmt(self):
        self.m.input(BGDTests.for_loop)
        start = 'for_stmt'
        self.parser = yacc.yacc(debug = False)
        root = self.parser.parse(tokenfunc = self.m.token)
        print Traverse(root).getJava()
        print  
          

if __name__ == "__main__":
	unittest.main(verbosity=2)

'''m = BGDLexer()
inputFile = open('tic-tac-toe.bgd')
#inputFile = open('tic-tac-toe.bgd')
inputData = inputFile.read()
#print inputData
m.input(inputData)
start = 'input_stmt'
parser = yacc.yacc()
result = parser.parse(tokenfunc = m.token)
code = Traverse(result).getJava()
print code'''