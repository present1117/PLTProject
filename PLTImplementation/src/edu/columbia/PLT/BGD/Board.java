package edu.columbia.PLT.BGD;
/**
 * Defination of the Board
 * @author Presenthuang
 *
 */
public class Board {
	static int parrow = 3;
	static int parcolumn = 3;
	static Slot [][] boardSlots = new Slot[parrow][parcolumn];
}

class Slot{
	Piece piece;
	Player owner;
	
	public Piece getPiece(){
		return piece;
	}
	
	public Player getPlayer() {
		return owner;
	}
	
	public boolean setPiece(Piece p) {
		piece = p;
		return true;
	}
	
	public boolean setPlayer(Player p) {
		owner = p;
		return true;
	}
}



class Pos{
	int posx;
	int posy;
	public Pos(int x, int y) {
		// TODO Auto-generated constructor stub
		posx = x;
		posy = y;
	}
}
