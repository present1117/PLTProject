
# parsetab.py
# This file is automatically generated. Do not edit.
_tabversion = '3.2'

_lr_method = 'LALR'

_lr_signature = '\x0bj\x0b\xac"0\xe2\x04\x04E~\xae\xb8\xeb\xd2\xf3'
    
_lr_action_items = {'DEDENT':([7,11,13,],[9,-3,-4,]),'PIECE':([0,],[1,]),'INDENT':([4,],[5,]),'STRING':([5,7,11,13,],[6,10,-3,-4,]),'NEWLINE':([3,8,12,],[4,11,13,]),'NUMBER':([6,10,],[8,12,]),':':([1,],[3,]),'$end':([2,9,],[0,-2,]),}

_lr_action = { }
for _k, _v in _lr_action_items.items():
   for _x,_y in zip(_v[0],_v[1]):
      if not _x in _lr_action:  _lr_action[_x] = { }
      _lr_action[_x][_k] = _y
del _lr_action_items

_lr_goto_items = {'piece_stmt':([0,],[2,]),'piece_expr':([5,],[7,]),}

_lr_goto = { }
for _k, _v in _lr_goto_items.items():
   for _x,_y in zip(_v[0],_v[1]):
       if not _x in _lr_goto: _lr_goto[_x] = { }
       _lr_goto[_x][_k] = _y
del _lr_goto_items
_lr_productions = [
  ("S' -> piece_stmt","S'",1,None,None,None),
  ('input_stmt -> piece_stmt board_stmt player_stmt rule_stmt function_stmt','input_stmt',5,'p_input_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',33),
  ('piece_stmt -> PIECE : NEWLINE INDENT piece_expr DEDENT','piece_stmt',6,'p_piece_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',37),
  ('piece_expr -> STRING NUMBER NEWLINE','piece_expr',3,'p_piece_expr','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',41),
  ('piece_expr -> piece_expr STRING NUMBER NEWLINE','piece_expr',4,'p_piece_expr','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',42),
  ('board_stmt -> BOARD : NEWLINE INDENT NUMBER NUMBER DEDENT','board_stmt',7,'p_board_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',49),
  ('player_stmt -> PLAYER : NEWLINE INDENT NUMBER DEDENT','player_stmt',6,'p_player_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',53),
  ('rule_stmt -> RULE : NEWLINE INDENT action_stmt DEDENT','rule_stmt',6,'p_rule_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',57),
  ('action_stmt -> ACTION : = ID NEWLINE','action_stmt',5,'p_action_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',61),
  ('action_stmt -> action_stmt ACTION : = ID NEWLINE','action_stmt',6,'p_action_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',62),
  ('function_stmt -> funcdef','function_stmt',1,'p_function_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',69),
  ('function_stmt -> function_stmt funcdef','function_stmt',2,'p_function_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',70),
  ('stmt -> simple_stmt','stmt',1,'p_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',77),
  ('stmt -> compound_stmt','stmt',1,'p_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',78),
  ('simple_stmt -> assign_stmt','simple_stmt',1,'p_simple_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',82),
  ('simple_stmt -> flow_stmt','simple_stmt',1,'p_simple_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',83),
  ('compound_stmt -> funcdef','compound_stmt',1,'p_compound_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',87),
  ('compound_stmt -> for_stmt','compound_stmt',1,'p_compound_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',88),
  ('compound_stmt -> if_stmt','compound_stmt',1,'p_compound_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',89),
  ('compound_stmt -> while_stmt','compound_stmt',1,'p_compound_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',90),
  ('assign_stmt -> ID : = expr NEWLINE','assign_stmt',5,'p_assign_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',94),
  ('flow_stmt -> break_stmt','flow_stmt',1,'p_flow_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',98),
  ('flow_stmt -> continue_stmt','flow_stmt',1,'p_flow_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',99),
  ('flow_stmt -> return_stmt','flow_stmt',1,'p_flow_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',100),
  ('funcdef -> DEF ID parameters : suite','funcdef',5,'p_funcdef','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',104),
  ('funcdef -> DEF ID : suite','funcdef',4,'p_funcdef','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',105),
  ('parameters -> parameters , parameter','parameters',3,'p_parameters','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',113),
  ('parameters -> parameter','parameters',1,'p_parameters','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',114),
  ('parameter -> ID','parameter',1,'p_parameter','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',121),
  ('parameter -> ID : = expr','parameter',4,'p_parameter','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',122),
  ('expr -> or_test','expr',1,'p_expr','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',129),
  ('or_test -> and_test','or_test',1,'p_or_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',133),
  ('or_test -> or_test OR and_test','or_test',3,'p_or_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',134),
  ('and_test -> not_test','and_test',1,'p_and_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',141),
  ('and_test -> and_test AND not_test','and_test',3,'p_and_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',142),
  ('not_test -> NOT not_test','not_test',2,'p_not_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',149),
  ('not_test -> comparison','not_test',1,'p_not_test','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',150),
  ('comparison -> operand','comparison',1,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',157),
  ('comparison -> comparison = operand','comparison',3,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',158),
  ('comparison -> comparison > operand','comparison',3,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',159),
  ('comparison -> comparison < operand','comparison',3,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',160),
  ('comparison -> comparison > = operand','comparison',4,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',161),
  ('comparison -> comparison < = operand','comparison',4,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',162),
  ('comparison -> comparison ~ = operand','comparison',4,'p_comparison','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',163),
  ('operand -> term','operand',1,'p_operand','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',172),
  ('operand -> operand + term','operand',3,'p_operand','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',173),
  ('operand -> operand - term','operand',3,'p_operand','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',174),
  ('term -> factor','term',1,'p_term','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',181),
  ('term -> term * factor','term',3,'p_term','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',182),
  ('term -> term / factor','term',3,'p_term','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',183),
  ('term -> term % factor','term',3,'p_term','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',184),
  ('factor -> power','factor',1,'p_factor','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',191),
  ('factor -> + factor','factor',2,'p_factor','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',192),
  ('factor -> - factor','factor',2,'p_factor','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',193),
  ('power -> atom','power',1,'p_power','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',202),
  ('power -> power trailer','power',2,'p_power','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',203),
  ('atom -> ID','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',210),
  ('atom -> STRING','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',211),
  ('atom -> NUMBER','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',212),
  ('atom -> BOOLEAN','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',213),
  ('atom -> NIL','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',214),
  ('atom -> func_expr','atom',1,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',215),
  ('atom -> ( expr )','atom',3,'p_atom','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',216),
  ('trailer -> . ID','trailer',2,'p_trailer','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',224),
  ('trailer -> [ expr ]','trailer',3,'p_trailer','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',225),
  ('func_expr -> ID ( )','func_expr',3,'p_func_expr','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',232),
  ('func_expr -> ID ( parameter_list )','func_expr',4,'p_func_expr','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',233),
  ('parameter_list -> expr','parameter_list',1,'p_parameter_list','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',240),
  ('parameter_list -> parameter_list , expr','parameter_list',3,'p_parameter_list','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',241),
  ('for_stmt -> FOR ID = NUMBER TO NUMBER : suite','for_stmt',8,'p_for_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',248),
  ('for_stmt -> FOR ID IN expr : suite','for_stmt',6,'p_for_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',249),
  ('if_stmt -> IF expr : suite','if_stmt',4,'p_if_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',256),
  ('if_stmt -> IF expr : suite elseif_stmt','if_stmt',5,'p_if_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',257),
  ('if_stmt -> IF expr : suite ELSE : suite','if_stmt',7,'p_if_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',258),
  ('if_stmt -> IF expr : suite elseif_stmt ELSE : suite','if_stmt',8,'p_if_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',259),
  ('elseif_stmt -> ELSEIF expr : suite','elseif_stmt',4,'p_elseif_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',270),
  ('elseif_stmt -> elseif_stmt ELSEIF expr : suite','elseif_stmt',5,'p_elseif_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',271),
  ('while_stmt -> WHILE expr : suite','while_stmt',4,'p_while_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',278),
  ('suite -> NEWLINE','suite',1,'p_suite','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',282),
  ('suite -> NEWLINE INDENT suite_stmt DEDENT','suite',4,'p_suite','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',283),
  ('suite_stmt -> stmt','suite_stmt',1,'p_suite_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',290),
  ('suite_stmt -> suite_stmt stmt','suite_stmt',2,'p_suite_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',291),
  ('return_stmt -> RETURN expr NEWLINE','return_stmt',3,'p_return_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',298),
  ('continue_stmt -> CONTINUE NEWLINE','continue_stmt',2,'p_continue_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',302),
  ('break_stmt -> BREAK NEWLINE','break_stmt',2,'p_break_stmt','/Users/dechuanxu/Documents/Columbia/Spring14/PLT/PLTProject/ply-3.4/yaccing.py',306),
]
