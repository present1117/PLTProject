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
		pos = new Pos(pos.x(), pos.y());
	}

	boolean setPiecetype(String type) {
		Type = type;
		return true;
	}

	String piecetype() {
		return Type;
	}
}
