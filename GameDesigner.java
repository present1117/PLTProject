package edu.columbia.PLT.BGD;
public class GameDesigner{
enum pieceType{stone};
static int [] pieceNum = {0};
static int boardRow = 3;
static int boardCol = 3;
static int playerNum = 2;
public static boolean add (int[] position)
{
return Functions.isEmpty(position);
}
public static boolean win (int[] position)
{
if(Functions.numberInRow(position)>=3)
{
return true;
}
else
{
return false;
}
}
}
