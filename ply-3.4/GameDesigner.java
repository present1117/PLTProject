package edu.columbia.PLT.BGD;
public class GameDesigner{
enum pieceType{STONE};
static int [] pieceNum = {0};
static int boardRow = 3;
static int boardCol = 3;
static int playerNum = 2;
public static boolean add_res (int[] position)
{
return Functions.isEmpty(position);
}
public static boolean win_res (int[] position)
{
if(Functions.numberInRow(position)>=3)
{
return true;
}
else if(a>3){
while(a<=4)
{
return false;
}
}
else
{
return true;
}
}
}
