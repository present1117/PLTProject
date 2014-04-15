package edu.columbia.PLT.BGD;
/**
 * Defination of the Board
 * @author Presenthuang
 *
 */
public class Board {
	static private int parrow = GameDesigner.boardRow;
	static private int parcol = GameDesigner.boardCol;
	static Slot [][] boardslots = new Slot[parrow][parcol];
	
	static int row(){
		return parrow;
	}
	
	static int col(){
		return parcol;
	}
}

class Slot{
	private Piece piece;
	private Player owner;
	
	Piece Piece(){
		return piece;
	}
	
	Player Player() {
		return owner;
	}
	
	boolean setPiece(Piece p) {
		piece = p;
		return true;
	}
	
	boolean setPlayer(Player p) {
		owner = p;
		return true;
	}
}

class Pos{
	private int posx;
	private int posy;
	Pos(int x, int y) {
		// TODO Auto-generated constructor stub
		posx = x;
		posy = y;
	}
	
	int x(){
		return posx;
	}
	int y(){
		return posy;
	}
}
