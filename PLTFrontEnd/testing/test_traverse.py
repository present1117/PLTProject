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

	def test_tic_tac_toe(self):
		self.m.input(BGDTests.tic_tac_toe)
		self.parser = yacc.yacc(debug = False)
		root = self.parser.parse(tokenfunc = self.m.token)
		print Traverse(root).getJava() + '\n******************************************************\n' 

	def test_test_code(self):
		self.m.input(BGDTests.test_code)
		self.parser = yacc.yacc(debug = False)
		root = self.parser.parse(tokenfunc = self.m.token)
		print Traverse(root).getJava() + '\n******************************************************\n'     	

	def test_init_example(self):
		self.m.input(BGDTests.init_example)
		self.parser = yacc.yacc(debug = False)
		root = self.parser.parse(tokenfunc = self.m.token)
		print Traverse(root).getJava() + '\n******************************************************\n'        

	'''def test_multication(self):
		self.m.input(BGDTests.multication)
		self.parser = yacc.yacc(debug = False)
		root = self.parser.parse(tokenfunc = self.m.token)
		self.out_str += Traverse(root).getJava() + '\n******************************************************\n'   
		'''

	def test_multipiece(self):
		self.m.input(BGDTests.multipiece)
		self.parser = yacc.yacc(debug = False)
		root = self.parser.parse(tokenfunc = self.m.token)
		print Traverse(root).getJava() + '\n******************************************************\n'  

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
