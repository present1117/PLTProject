import java.lang.*;
import java.util.*;
public class GameDesigner{
enum pieceType{RED,YELLOW,GREEN,BLUE};
static int[] pieceNum = {0,0,0,0};
static int boardRow = 8;
static int boardCol = 8;
static int playerNum = 1;
public static boolean add_res (int piece,int[] position)
{
int PIECE_TYPE=Functions.getPieceType(piece);

if(PIECE_TYPE==RED||PIECE_TYPE==YELLOW)
{
return Functions.isEmpty(position);
}

if(PIECE_TYPE==GREEN)
{
return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece({position[0], position[1]-1}))==RED&&Functions.getPieceType(Functions.getPiece({position[0], position[1]+1}))==RED&&Functions.getPieceType(Functions.getPiece({position[0]-1, position[1]}))==RED&&Functions.getPieceType(Functions.getPiece({position[0]+1, position[1]}))==RED;
}

if(PIECE_TYPE==BLUE)
{
return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece({position[0], position[1]-1}))==RED&&Functions.getPieceType(Functions.getPiece({position[0]-1, position[1]}))==RED&&Functions.getPieceType(Functions.getPiece({position[0], position[1]+1}))==YELLOW&&Functions.getPieceType(Functions.getPiece({position[0]+1, position[1]}))==YELLOW;
}
}
public static boolean win_res (int[] position)
{
if(Functions.pieceCount(GREEN)+Functions.pieceCount(BLUE)==15)
{
return true;
}
else
{
return false;
}
}
public static boolean move_res(int[] par0,int[] par1)
{
return true;
}
public static boolean remove_res(int[] par0)
{
return true;
}
}
