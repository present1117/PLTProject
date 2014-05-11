import unittest
import types
import sys
import re

sys.path.append('..')
import lexing

class testLexing(unittest.TestCase):
    def setUp(self):
        self.m = lexing.BGDLexer()

    def test_tokens(self):
        cases = {
            'global' : 'GLOBAL',
            'FUNCTION' : 'FUNCTION',
            '12345' : 'NUMBER',
            '-435384.2523' : 'NUMBER',
            'NO' : 'BOOLEAN',
            "'fhasudfasvsgw87w8ae7r8923b'" : 'STRING',
            "'I want to say:\tThis is awesome!'" : 'STRING',
            "''" : 'STRING',
            'w_32324w':'ID',
            'Zacard' : 'ID',
            '@@asdsafasfasjv' : 'COMMENT',
            }
        self.assertTokensEq(cases)

    def assertTokensEq(self, cases):
        for key, value in cases.iteritems():
            self.m.input(key)
            token = self.m.token()
            # Special case for comment
            if value == 'COMMENT':      
                self.assertIsInstance(token, types.NoneType)
            else:
                self.assertEqual(value, token.type)
                self.assertEqual(key, token.value)
                

    def test_indent(self):
            f = open('test_indent.bgd')
            correct_answer = ['NEWLINE', 'INDENT', 'NEWLINE', 'NEWLINE', 'INDENT', 'NEWLINE', 'INDENT',\
                             'NEWLINE', 'NEWLINE', 'INDENT', 'NEWLINE', 'DEDENT','NEWLINE', 'DEDENT',\
                             'NEWLINE', 'DEDENT', 'NEWLINE', 'DEDENT', 'NEWLINE', 'INDENT',\
                             'NEWLINE', 'INDENT', 'NEWLINE', 'DEDENT', 'DEDENT', 'NEWLINE', 'NEWLINE']
            case = f.read()
            self.m.input(case)
            idx = 0
            while True:
                tok = self.m.token()
                if not tok:
                    break
                if tok.type in ['NEWLINE', 'INDENT', 'DEDENT']:
                    self.assertEqual(tok.type, correct_answer[idx])
                    idx += 1
            self.assertEqual(idx, len(correct_answer))

    def test_sample_code(self):
        f1 = open('tic-tac-toe.bgd', 'r')
        code = f1.read()
        f2 = open('tic-tac-toe-tokens', 'r')
        tokens = f2.read()
        tokens = re.split('\n+', tokens)
        self.m.input(code)
        print len(tokens)
        for token in tokens:
            tmp_token = self.m.token()
            self.assertEqual(tmp_token.type, token)
        f1.close()
        f2.close()
        
        
        
        

if __name__ == "__main__":
    unittest.main(verbosity=2)
