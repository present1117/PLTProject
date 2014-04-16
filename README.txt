-TeamNo: 7
-Date: Apr 15, 2014

-File
lexing.py
yaccing.py
traverse.py

-running code
>>$python traverse.py inputFile.bgd
Example: if your input code is named as tic-tac-toe.bgd
>>$python traverse.py tic-tac-toe.bgd

-output file
GameDesigner.java
, which contains a java class named GameDesigner

-class members
enum pieceType
static int[] pieceNum
static int boardRow, boardCol, playerNum

-class methods
public static boolean add_res(int[] position)
public static boolean win_res(int[] position)
public static boolean remove_res(int[] position)
public static boolean move_res(int[] position, int[] position)

note: 1. if the function is not defined in input code, the function will always return ture. 
2. there may be some private functions that defined by coder

Limitations:
1. private functions must be put ahead of action functions definition
2. only support array with same type of variables
