/**
 * Defination of the Board
 * 
 * @author Presenthuang
 * 
 */
public class Board {
	static private int parrow = GameDesigner.boardRow;
	static private int parcol = GameDesigner.boardCol;
	static private Slot[][] boardSlots = new Slot[parrow][parcol];

	public static int getRow() {
		return parrow;
	}

	public static int getCol() {
		return parcol;
	}

	public static Slot[][] getBoardSlots() {
		return boardSlots;
	}

	public static Slot getSlot(Pos pos) {
		return boardSlots[pos.x()][pos.y()];
	}

	public static boolean initSlot(Pos pos) {
		if ((boardSlots[pos.x()][pos.y()] = new Slot()) != null)
			return true;
		else
			return false;
	}
}

class Slot {
	private Piece piece;
	private Player owner;

	Piece Piece() {
		return piece;
	}

	Player Player() {
		return owner;
	}

	boolean setPiece(Piece p, Player player) {
		piece = p;
		owner = player;
		return true;
	}

	boolean available() {
		if (piece == null && owner == null) {
			return true;
		}
		return false;
	}
}

class Pos {
	private int x;
	private int y;

	Pos(int _x, int _y) {
		x = _x;
		y = _y;
	}

	Pos(Pos pos) {
		x = pos.x();
		y = pos.y();
	}
	Pos(String s){
		String[] pos = s.split(",");
		if (pos.length != 2) {
			
		}
	}

	int x() {
		return x;
	}

	int y() {
		return y;
	}
}