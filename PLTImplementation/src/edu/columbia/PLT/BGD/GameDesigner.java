package edu.columbia.PLT.BGD;

public class GameDesigner {
	enum newPieceType {STONE}
	static int [] Piecenum = {0};
	
	static int boardRow = 3;
	static int boardCol = 3;
	
	static int playerNum = 2;
	
	public static boolean add_res(int posx, int posy) {
		return Functions.isEmpty(posx, posy);
	}
	
	public static boolean win_res(int posx, int posy) {
		if(Functions.numberInRow(posx, posy) >= 3){
			return true;
		}else {
			return false;
		}
	}
}
