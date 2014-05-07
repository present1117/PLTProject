/**
 * Defination of Piece, have the properties.
 * 
 * @author Presenthuang
 * 
 */
public class Piece {
	Player owner;
	Pos pos;
	String Type = "";

	Piece(Player p, Pos pos) {
		// TODO Auto-generated constructor stub
		owner = p;
		pos = new Pos(pos.getX(), pos.getY());
	}

	boolean setPiecetype(String type) {
		Type = type;
		return true;
	}

	String piecetype() {
		return Type;
	}
}
