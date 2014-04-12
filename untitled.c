input_stmt:
	piece_stmt board_stmt player_stmt rule_stmt function_stmt

piece_stmt:
	PIECE ‘:’ NEWLINE INDENT piece_expr DEDENT

piece_expr:
	STRING NUMBER NEWLINE
	piece_expr STRING NUMBER NEWLINE

board_stmt:
	BOARD ‘:’ NEWLINE INDENT NUMBER NUMBER DEDENT

player_stmt:
	PLAYER ‘:’ NEWLINE INDENT NUMBER DEDENT

rule_stmt:
	RULE ‘:’ NEWLINE INDENT action_stmt DEDENT

action_stmt:
	‘action’ ‘:=’ action_name NEWLINE
     action_stmt ‘action’ ‘:=’ action_name NEWLINE

action_name:
	add
	move
	win
	NAME

function_stmt:
	funcdef
	function_stmt funcdef

stmt:
	simple_stmt
	compound_stmt

simple_stmt:
	assign_stmt
	flow_stmt


compound_stmt:
	funcdef
	for_stmt
	if_stmt
	while_stmt

assign_stmt:
	NAME ‘:=’ expr NEWLINE
	assign_stmt NAME ‘:=’ expr NEWLINE

flow_stmt:
break_stmt
continue_stmt
return_stmt

funcdef : 
‘def’ NAME parameters ‘:’ suite
‘def’ NAME ‘:’ suite


parameters:
parameters ’,’ parameter
parameter

parameter:
	NAME
	NAME ‘:=’ expr

expr:
	or_test

or_test: 
and_test
or_test ‘OR’ and_test

and_test:
	not_test
	and_test ‘AND’ not_test

not_test:
	‘NOT’ not_test
	comparison

comparison:
	operand
	comparison comp_op operand

comp_op:
‘=’
‘>’
‘<’
‘>=’
‘<=’
‘~=’

operand:
	term 
	operand ‘+’ term
	operand ‘-’ term

term:
	factor
	term ‘*’ factor
	term ‘/’ factor
	term ‘%’ factor

	
factor:
	power
	‘+’ factor
	‘-’ factor

power:
	atom 				{$$ = $1}
	power trailer 		{$$ = $1}

atom:	NAME 			{$$ = string($1);}
	| 	string 			{$$ = string($1);}
	| 	NUMBER			{$$ = number($1);}
	|	BOOLEAN			{$$ = boolean($1);}
	|	NIL				{$$ = "NULL";}
	|	func_expr		{$$ = $1;}
	|‘(’ expr ‘)		{$$ = $2;}	

trailer:
	‘.’ NAME
	‘[’ expr ‘]’

func_expr:
	NAME ‘(’ ‘)’
	NAME ‘(’ parameter_list ‘)’
	

parameter_list:
	expr
	parameter_list ‘,’ expr

for_stmt:
	FOR NAME ‘=’ NUMBER TO NUMBER ‘:’ suite
	FOR NAME IN expr ‘:’ suite

if_stmt: 
IF expr ‘:’ suite
IF expr ‘:’ suite elseif_stmt
IF expr ‘:’ suite ELSE ‘:’ suite
IF expr ‘:’ suite elseif_stmt ELSE ‘:’ suite

elseif_stmt:
ELSEIF expr ':' suite
elseif_stmt ELSEIF expr ':' suite

while_stmt: 
WHILE expr ':' suite 

suite: 
	NEWLINE
NEWLINE INDENT suite_stmt DEDENT

suite_stmt:
	stmt
	suite_stmt stmt

break_stmt:
	BREAK NEWLINE

continue_stmt:
	CONTINUE NEWLINE

return_stmt:
	RETURN expr NEWLINE