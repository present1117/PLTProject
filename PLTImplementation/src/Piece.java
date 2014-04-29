/**
 * Defination of Piece, have the properties.
 * 
 * @author Presenthuang
 * 
 */
public class Piece {
	Player owner;
	Pos pos;
	int Type = 0;

	Piece(Player p, Pos pos) {
		// TODO Auto-generated constructor stub
		owner = p;
		pos = new Pos(pos.x(), pos.y());
	}

	boolean setPiecetype(int type) {
		Type = type;
		return true;
	}

	int piecetype() {
		return Type;
	}
}
