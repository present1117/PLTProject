package edu.columbia.PLT.BGD;
/**
 * Defination of Piece, have the properties.
 * @author Presenthuang
 *
 */
public class Piece {
	enum PieceType {STONE};
	Player owner;
	Pos pos;
	int Type = 0;
	
    Piece(Player p) {
		// TODO Auto-generated constructor stub
		owner = p;
		pos = new Pos(0,0);
	}
	
	public boolean setPiecetype(int type){
		Type = type;
		return true;
	}
	
	public int piecetype(){
		return Type;
	}
}
