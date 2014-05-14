import java.lang.*;
import java.util.*;
public class GameDesigner{
static String[] pieceType = {"STONE"};
static int[] pieceNum = {0};
static int boardRow = 3;
static int boardCol = 3;
static int playerNum = 1;

static String[] initPieces = {"STONE","STONE","STONE","STONE","STONE","STONE","STONE","STONE","STONE"};
static int[] initOwner = {0,0,0,0,0,0,0,0,0};
static int[][] initPos = {{0, 0},{0, 1},{0, 2},{1, 0},{1, 1},{1, 2},{2, 0},{2, 1},{2, 2}};

public static boolean remove_res (Pos position)
{
if(Functions.getPiece(position)!=null)
{
return true;
}
else
{
return false;
}
}
public static boolean win_res (Pos position)
{
if(Functions.pieceCount("STONE")==0)
{
return true;
}
else
{
return false;
}
}
public static boolean add_res(String par0,Pos par1)
{
return false;
}
public static boolean move_res(Pos par0,Pos par1)
{
return false;
}
}
