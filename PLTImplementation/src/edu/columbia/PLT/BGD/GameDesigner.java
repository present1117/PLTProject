package edu.columbia.PLT.BGD;



public class GameDesigner {
	enum newPieceType {STONE}
	int [] Piecenum = {0};
	
	int boardRow = 3;
	int boardCol = 3;
	
	int playerNum = 2;
	
	public static boolean add(int posx, int posy) {
		return Functions.isEmpty(posx, posy);
	}
	
	public static boolean win(int posx, int posy) {
		if(Functions.numberInRow(posx, posy) >= 3){
			return true;
		}else {
			return false;
		}
	}
}
