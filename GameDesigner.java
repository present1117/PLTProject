package edu.columbia.PLT.BGD;
import java.lang.String;
import java.util.*;
public class GameDesigner{
enum pieceType{STONE};
static int [] pieceNum = {0};
static int boardRow = 3;
static int boardCol = 3;
static int playerNum = 2;
public static boolean add_res (int[] position)
{
int[] pos={4, 4};

String[] a={"1", "2", "3"};

return Functions.isEmpty(position);
}
public static boolean win_res (int[] position)
{
int a=4;

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
public static boolean move_res(int[] par0,int[] par1)
{
return true;
}
public static boolean remove_res(int[] par0)
{
return true;
}
}
