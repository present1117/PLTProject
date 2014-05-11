#!/usr/bin/python

"""test_yaccing.py for BGD (Board Game Design)
"""
__teamNo__ = 7
__date__ = "May 10, 2014"
import sys

sys.path.append('..')
import unittest
import sys
import ply.yacc as yacc
import lexing
import re
import yaccing
from test_cases import BGDTests

class testYaccing(unittest.TestCase):
    def setUp(self):
        self.m = lexing.BGDLexer()
        self.parser = yacc.yacc(debug = False)

    def test_piece_stmt(self):
        self.m.input(BGDTests.piece_stmt)
        start = 'piece_stmt'
        print self.parser.parse(tokenfunc = self.m.token)

if __name__ == "__main__":
    yaccing.start = 'piece_stmt'
    m = lexing.BGDLexer()
    f = open('tic-tac-toe.bgd')
    line = f.read()
    m.input(line)
    print parser.parse(tokenfunc = m.token)
