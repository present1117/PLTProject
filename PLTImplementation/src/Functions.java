import java.util.ArrayList;


/**
 * class for all functions in the program as well all the built-in functions
 * 
 * @author Presenthuang
 * 
 */

public class Functions {
	public static boolean add(int posx, int posy, Player currentowner, String ptype) {
		if (GameDesigner.add_res(ptype, new Pos(posx, posy))) {
			Board.boardslots[posx][posy] = new Slot();
			Board.boardslots[posx][posy].setPiece(new Piece(currentowner,
					new Pos(posx, posy)), currentowner);
			return true;
		} else {
			return false;
		}
	}

	public static boolean win(int posx, int posy, Player currentowner) {
		if (GameDesigner.win_res(new int[] { posx, posy })) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isEmpty(Pos position) {
		if (position == null)
			return false;
		if (position.x() >= Board.boardslots.length
				|| position.y() >= Board.boardslots[0].length || position.x() < 0 || position.y() < 0)
			return false;
		Slot currentSlot = Board.boardslots[position.x()][position.y()];
		if (currentSlot == null) {
			return true;
		} else {
			return false;
		}
	}

	public static int numberInRow(int[] position) {
		if (position.length < 2)
			return 0;
		int posx = position[0];
		int posy = position[1];
		Slot currentSlot = Board.boardslots[posx][posy];
		if (currentSlot == null) {
			return 0;
		}
		// check horizontal line
		// check vertical line
		// check diagonal line
		int a = CountDiagonal(posx, posy, currentSlot);
		int b = CountHorizontal(posx, posy, currentSlot);
		int c = CountVertical(posx, posy, currentSlot);

		return Math.max(a, Math.max(b, c));
	}

	private static int CountHorizontal(int posx, int posy, Slot currentSlot) {
		int total = 1;
		for (int i = posy + 1; i < Board.col(); ++i) {
			if (Board.boardslots[posx][i] == null)
				break;
			if (Board.boardslots[posx][i].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}
		for (int i = posy - 1; i >= 0; --i) {
			if (Board.boardslots[posx][i] == null)
				break;
			if (Board.boardslots[posx][i].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}

		return total;
	}

	private static int CountVertical(int posx, int posy, Slot currentSlot) {
		int total = 1;
		for (int i = posx + 1; i < Board.row(); ++i) {
			if (Board.boardslots[i][posy] == null)
				break;
			if (Board.boardslots[i][posy].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}
		for (int i = posx - 1; i >= 0; --i) {
			if (Board.boardslots[i][posy] == null)
				break;
			if (Board.boardslots[i][posy].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}

		return total;
	}

	private static int CountDiagonal(int posx, int posy, Slot currentSlot) {
		int i = posx;
		int j = posy;
		int total1 = 1;
		int total2 = 1;

		i -= 1;
		j -= 1;
		while (i >= 0 && i < Board.row() && j >= 0 && j < Board.col()) {
			if (Board.boardslots[i][j] == null)
				break;
			if (Board.boardslots[i][j].Player() == currentSlot.Player()) {
				total1++;
			}
			i -= 1;
			j -= 1;
		}
		i = posx;
		j = posy;
		i += 1;
		j += 1;
		while (i >= 0 && i < Board.row() && j >= 0 && j < Board.col()) {
			if (Board.boardslots[i][j] == null)
				break;
			if (Board.boardslots[i][j].Player() == currentSlot.Player()) {
				total1++;
			}
			i += 1;
			j += 1;
		}

		i = posx;
		j = posy;
		i -= 1;
		j += 1;
		while (i >= 0 && i < Board.row() && j >= 0 && j < Board.col()) {
			if (Board.boardslots[i][j] == null)
				break;
			if (Board.boardslots[i][j].Player() == currentSlot.Player()) {
				total2++;
			}
			i -= 1;
			j += 1;
		}

		i = posx;
		j = posy;
		i += 1;
		j -= 1;
		while (i >= 0 && i < Board.row() && j >= 0 && j < Board.col()) {
			if (Board.boardslots[i][j] == null)
				break;
			if (Board.boardslots[i][j].Player() == currentSlot.Player()) {
				total2++;
			}
			i += 1;
			j -= 1;
		}

		return total1 > total2 ? total1 : total2;
	}

	public static String getPieceType(Piece p) {
		return p.piecetype();
	}

	public static Player getPiecePlayer(Piece p) {
		return p.owner;
	}
	
	public static ArrayList<Piece> getPiecefromPlayer(Player p, String type){
		ArrayList<Piece> myList = new ArrayList<Piece>();
		for(Piece pi : p.pieceList){
			if(pi.Type.equals(type))
				myList.add(pi);
		}
		return myList;
	}
	
	public static ArrayList<Piece> getPiecefromPlayer(Player p){
		return p.pieceList;
	}

	public static Pos getPiecePos(Piece p) {
		return p.pos;
	}

	public static Piece getPiece(Pos po) {
		if(po.x() < Board.boardslots.length && po.y() < Board.boardslots[0].length){
			Piece piece = Board.boardslots[po.x()][po.y()].Piece();
			return piece;
		}
		return null;
	}
	
	public static Piece getPiece(int i, int j) {
		// TODO Auto-generated method stub
		if(i < Board.boardslots.length && j < Board.boardslots[0].length){
			Piece piece = Board.boardslots[i][j].Piece();
			return piece;
		}
		return null;
	}

	public static int pieceCount(String piecetype) {
		int count = 0;
		for (int x = 0; x < Board.boardslots.length; x++) {
			for (int y = 0; y < Board.boardslots[0].length; y ++) {
				if (Board.boardslots[x][y] != null && Board.boardslots[x][y].Piece() != null && Board.boardslots[x][y].Piece().piecetype().equals(piecetype))
					count ++;
			}
		}
		return count;
	}

	public static boolean isBoardFull() {
		for (int x = 0; x < Board.boardslots.length; x++) {
			for (int y = 0; y < Board.boardslots[0].length; y ++) {
				if (Board.boardslots[x][y].available())
					return false;
			}
		}
		return true;
	}

	public static boolean remove(Pos po, ArrayList<Player> players) {
		Piece piece = Board.boardslots[po.x()][po.y()].Piece();
		if (piece == null) {
			return false;
		} else {
			Player owner = piece.owner;
			Board.boardslots[po.x()][po.y()].setPiece(null, null);
			// delete the piece from the player
			for (Player p : players) {
				if (p.getId() == owner.getId()) {
					p.removePiece(piece);
				}
			}
			return true;
		}
	}
	void getPiecefromPlayer() {
		
	}

	public static Piece findNextInRow(Pos pos, int mode) {
		int x = pos.x();
		int y = pos.y();
		Slot currentSlot = Board.boardslots[x][y];
		if (currentSlot == null || currentSlot.Piece() == null) return null;
		if (mode < 0 || mode > 7) return null;
		int x_min = 0;
		int y_min = 0;
		int x_max = Board.boardslots.length - 1;
		int y_max = Board.boardslots[0].length - 1;
		
		while (x >= x_min && x <= x_max && y >= y_min && y <= y_max) {
			Slot slot = Board.boardslots[x][y];
			if (slot != null && slot.Piece() != null && slot.Piece().Type == currentSlot.Piece().piecetype() && slot.Piece().owner.getId() == currentSlot.Piece().owner.getId())
					return Board.boardslots[x][y].Piece();
			if (mode <= 1 || mode == 7) x --;
			if (mode >= 3 && mode <= 5) x ++;
			if (mode >= 5 && mode <= 7) y --;
			if (mode >= 1 && mode <= 3) y ++;
		}
		return null;
	}

}
