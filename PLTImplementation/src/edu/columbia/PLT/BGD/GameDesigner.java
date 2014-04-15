package edu.columbia.PLT.BGD;
public class GameDesigner{
enum pieceType{STONE};
static int [] pieceNum = {0};
static int boardRow = 5;
static int boardCol = 5;
static int playerNum = 2;
public static boolean add_res (int[] position)
{
return Functions.isEmpty(position);
}
public static boolean win_res (int[] position)
{
if(Functions.numberInRow(position)>=5)
{
return true;
}
else
{
return false;
}
}
public static boolean move_res(int[] par1,int[] par2)
{
return true;
}
public static boolean remove_res(int[] par1)
{
return true;
}
}