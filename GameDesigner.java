import java.lang.*;
import java.util.*;
public class GameDesigner{
static String[] pieceType = {"RED","YELLOW","GREEN","BLUE"};
static int[] pieceNum = {0,0,0,0};
static int boardRow = 8;
static int boardCol = 8;
static int playerNum = 1;

public static boolean add_res (String pieceType,Pos position)
{
Object PIECE_TYPE=pieceType;

if(PIECE_TYPE=="RED"||PIECE_TYPE=="YELLOW")
{
return Functions.isEmpty(position);
}

if(PIECE_TYPE=="GREEN")
{
return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()-1))=="RED"&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()+1))=="RED"&&Functions.getPieceType(Functions.getPiece(position.x()-1, position.y()))=="RED"&&Functions.getPieceType(Functions.getPiece(position.x()+1, position.y()))=="RED";
}

if(PIECE_TYPE=="BLUE")
{
return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()-1))=="RED"&&Functions.getPieceType(Functions.getPiece(position.x()-1, position.y()))=="RED"&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()+1))=="YELLOW"&&Functions.getPieceType(Functions.getPiece(position.x()+1, position.y()))=="YELLOW";
}

return false;
}
public static boolean win_res (Pos position)
{
if(Functions.pieceCount("GREEN")+Functions.pieceCount("BLUE")==15)
{
return true;
}
else
{
return false;
}
}
static String[] initPieces = {};
static int[] initOwner = {};
static int[][] initPos = {};
public static boolean move_res(Pos par0,Pos par1)
{
return true;
}
public static boolean remove_res(Pos par0)
{
return true;
}
}
