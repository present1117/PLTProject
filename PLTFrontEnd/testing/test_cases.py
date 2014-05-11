class BGDTests(object):
    piece_stmt = """
PIECE:
    'RED' 34
    'YELLOW' 13
    'GREEN' 9
    'BLUE' 7"""

    board_stmt = """
BOARD:
	19 19"""

    player_stmt = """
PLAYER:
	2"""
    
    rule_stmt = """
RULE:
	action	:= add
	action	:= remove
	action	:= win"""

    if_stmt = """
if i > 0:
    j := i
elseif i = 0:
    j := 5
else:
    j := -i
    """

    funcdef = """
def add piece, position:
		return isEmpty(position)"""

    for_loop = """
for i := 1 to 10:
    a := pos(i)"""

    forin_loop = """
for pos in positions:
    pos.x := 0"""
    
    while_loop = """
while i ~= 0:
    i := i - 1"""

    sample_code = """
PIECE:
    'stone'

BOARD:
    3 3

PLAYER:
    2

RULE:
    action := add
    action := win

FUNCTION:
       def add piece, position:
        return isEmpty(position)
       def win position:
        if numberInRow(position) >= 3:
            return YES
        else:
            return NO"""

    init_example = """
PIECE:
    'STONE'

BOARD:
    3 3

PLAYER:
    1

RULE:
    action := remove
    action := win

FUNCTION:
       def __init__:
        'STONE' 0 (0,0)
        'STONE' 0 (0,1)
        'STONE' 0 (0,2)
        'STONE' 0 (1,0)
        'STONE' 0 (1,1)
        'STONE' 0 (1,2)
        'STONE' 0 (2,0)
        'STONE' 0 (2,1)
        'STONE' 0 (2,2)

       def remove position:
        if getPiece(position) ~= NIL:
            return YES
        else:
            return NO

       def win position:
        if pieceCount('STONE') = 0:
            return YES
        else:
            return NO"""

    multication = """
PIECE:
    'STONE'
BOARD:
    19 19
PLAYER:
    2
RULE:
    action  := add
    action  := remove
    action  := win
    
FUNCTION:
    def add piece, position:
        return isEmpty(position)

    def remove position:
        positions := getAllPieces()
        Remove := create2DimArray(19,19)
        flag := NO
        for pos in positions:
            if pos.x = 0:
                if pos.y = 0:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                elseif pos.y = 18:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                else:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
            elseif pos.x = 18:
                if pos.y = 0:
                    if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                elseif pos.y = 18:
                    if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                else:
                    if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
            elseif pos.y = 0:
                if pos.x = 0:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                elseif pos.x = 18:
                    if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                else:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND Remove[pos.x- 1][pos.y] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
            elseif pos.y = 18:
                if pos.x = 0:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                elseif pos.x = 18:
                    if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                        flag := YES
                else:
                    if Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y- 1] = YES AND Remove[pos.x- 1][pos.y] = YES AND getPiece(pos) ~= NIL:
                        flag := YES 
            else:
                if Remove[pos.x- 1][pos.y] = YES AND Remove[pos.x+ 1][pos.y] = YES AND Remove[pos.x][pos.y+ 1] = YES AND Remove[pos.x][pos.y- 1] = YES AND getPiece(pos) ~= NIL:
                    flag := YES
        if flag:
            mySet := findSameInCircle(position)
            for point in mySet:
                Remove[point.x][point.y]:= YES
        for pos in positions:
            if Remove[pos.x][pos.y] = YES:
                remove(pos)

    def win position:
        if isBoardFull():
            player1pieces := 0
            player2pieces := 0
            if getPiecePlayer(getPiece(position)).id = 1:
                player1pieces := player1pieces+ 1
            elseif getPiecePlayer(getPiece(position)).id = 2:
                player2pieces := player2pieces+ 1
                if player1pieces > player2pieces:
                    return 1
            else:
                return 2    
        return -1"""

    multipiece = """
PIECE:
    'RED'
    'YELLOW'
    'GREEN'
    'BLUE'

BOARD:
    8 8

PLAYER:
    1

RULE:
    action := add
    action := win

FUNCTION:
    def add pieceType, position:
        PIECE_TYPE := pieceType
        if PIECE_TYPE='RED' OR PIECE_TYPE='YELLOW':
            return isEmpty(position)
        if PIECE_TYPE='GREEN':
            return isEmpty(position) AND getPieceType(getPiece(position.x,position.y- 1))='RED' AND getPieceType(getPiece(position.x,position.y + 1))='RED' AND getPieceType(getPiece(position.x - 1,position.y))='RED' AND getPieceType(getPiece(position.x + 1,position.y))='RED'
        
        if PIECE_TYPE='BLUE':
            return isEmpty(position) AND getPieceType(getPiece(position.x,position.y - 1))='RED' AND getPieceType(getPiece(position.x - 1,position.y))='RED' AND getPieceType(getPiece(position.x,position.y + 1))='YELLOW' AND getPieceType(getPiece(position.x + 1,position.y))='YELLOW'
        return NO


    def win position: 
        if pieceCount('GREEN') + pieceCount('BLUE') = 15:
            return YES
        else:
            return NO
"""
    
