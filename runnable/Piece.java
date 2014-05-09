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

	Piece(Player p, Pos po) {
		// TODO Auto-generated constructor stub
		owner = p;
		pos = new Pos(po.x(), po.y());
	}

	public Piece(Player currentowner, Pos pos2, String ptype) {
		// TODO Auto-generated constructor stub
		owner = currentowner;
		pos = new Pos(pos2.x(), pos2.y());
		Type = ptype;
	}

	boolean setPiecetype(String type) {
		Type = type;
		return true;
	}

	String getPiecetype() {
		return Type;
	}
}
