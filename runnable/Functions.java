import java.util.ArrayList;

/**
 * class for all functions in the program as well all the built-in functions
 * 
 * @author Presenthuang
 * 
 */

public class Functions {
	public static boolean add(Pos pos, Player currentowner, String ptype) {
		if (GameDesigner.add_res(ptype, new Pos(pos))) {
			Board.initSlot(pos);
			Board.getSlot(pos).setPiece(
					new Piece(currentowner, new Pos(pos), ptype), currentowner);
			return true;
		} else {
			return false;
		}
	}

	public static boolean win(Pos pos, Player winningPlayer) {
		Object wincon = GameDesigner.win_res(new Pos(pos));
		if (wincon.getClass().getName().contains("Integer")) {
			if ((Integer) wincon == -1)
				return false;
			else {
				winningPlayer.setId((Integer) wincon);
				return true;
			}

		} else if (wincon.getClass().getName().contains("Boolean")) {
			if ((Boolean) wincon) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public static boolean isEmpty(Pos pos) {
		if (pos == null)
			return false;
		if (pos.x() >= Board.getBoardSlots().length
				|| pos.y() >= Board.getBoardSlots()[0].length || pos.x() < 0
				|| pos.y() < 0)
			return false;
		Slot currentSlot = Board.getSlot(pos);
		if (currentSlot == null) {
			return true;
		} else {
			return false;
		}
	}

	public static int numberInRow(Pos pos) {
		Slot currentSlot = Board.getSlot(pos);
		if (currentSlot == null) {
			return 0;
		}
		// check horizontal line
		// check vertical line
		// check diagonal line
		int posx = pos.x();
		int posy = pos.y();
		int a = CountDiagonal(posx, posy, currentSlot);
		int b = CountHorizontal(posx, posy, currentSlot);
		int c = CountVertical(posx, posy, currentSlot);

		return Math.max(a, Math.max(b, c));
	}

	private static int CountHorizontal(int posx, int posy, Slot currentSlot) {
		int total = 1;
		for (int i = posy + 1; i < Board.getCol(); ++i) {
			if (Board.getBoardSlots()[posx][i] == null)
				break;
			if (Board.getBoardSlots()[posx][i].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}
		for (int i = posy - 1; i >= 0; --i) {
			if (Board.getBoardSlots()[posx][i] == null)
				break;
			if (Board.getBoardSlots()[posx][i].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}

		return total;
	}

	private static int CountVertical(int posx, int posy, Slot currentSlot) {
		int total = 1;
		for (int i = posx + 1; i < Board.getRow(); ++i) {
			if (Board.getBoardSlots()[i][posy] == null)
				break;
			if (Board.getBoardSlots()[i][posy].Player() == currentSlot.Player()) {
				total++;
			} else {
				break;
			}
		}
		for (int i = posx - 1; i >= 0; --i) {
			if (Board.getBoardSlots()[i][posy] == null)
				break;
			if (Board.getBoardSlots()[i][posy].Player() == currentSlot.Player()) {
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
		while (i >= 0 && i < Board.getRow() && j >= 0 && j < Board.getCol()) {
			if (Board.getBoardSlots()[i][j] == null)
				break;
			if (Board.getBoardSlots()[i][j].Player() == currentSlot.Player()) {
				total1++;
			}
			i -= 1;
			j -= 1;
		}
		i = posx;
		j = posy;
		i += 1;
		j += 1;
		while (i >= 0 && i < Board.getRow() && j >= 0 && j < Board.getCol()) {
			if (Board.getBoardSlots()[i][j] == null)
				break;
			if (Board.getBoardSlots()[i][j].Player() == currentSlot.Player()) {
				total1++;
			}
			i += 1;
			j += 1;
		}

		i = posx;
		j = posy;
		i -= 1;
		j += 1;
		while (i >= 0 && i < Board.getRow() && j >= 0 && j < Board.getCol()) {
			if (Board.getBoardSlots()[i][j] == null)
				break;
			if (Board.getBoardSlots()[i][j].Player() == currentSlot.Player()) {
				total2++;
			}
			i -= 1;
			j += 1;
		}

		i = posx;
		j = posy;
		i += 1;
		j -= 1;
		while (i >= 0 && i < Board.getRow() && j >= 0 && j < Board.getCol()) {
			if (Board.getBoardSlots()[i][j] == null)
				break;
			if (Board.getBoardSlots()[i][j].Player() == currentSlot.Player()) {
				total2++;
			}
			i += 1;
			j -= 1;
		}

		return total1 > total2 ? total1 : total2;
	}

	public static String getPieceType(Piece p) {
		if (p != null)
			return p.getPiecetype();
		return null;
	}

	public static Player getPiecePlayer(Piece p) {
		return p.owner;
	}

	public static ArrayList<Piece> getPiecefromPlayer(Player p, String type) {
		ArrayList<Piece> myList = new ArrayList<Piece>();
		for (Piece pi : p.pieceList) {
			if (pi.Type.equals(type))
				myList.add(pi);
		}
		return myList;
	}

	public static ArrayList<Piece> getPiecefromPlayer(Player p) {
		return p.pieceList;
	}

	public static Pos getPiecePos(Piece p) {
		return p.pos;
	}

	public static Piece getPiece(Pos po) {
		if (po.x() < Board.getBoardSlots().length
				&& po.y() < Board.getBoardSlots()[0].length) {
			Piece piece = Board.getBoardSlots()[po.x()][po.y()].Piece();
			return piece;
		}
		return null;
	}

	public static Piece getPiece(int i, int j) {
		if (i >= 0 && j >= 0 && i < Board.getBoardSlots().length
				&& j < Board.getBoardSlots()[0].length) {
			Piece piece = Board.getBoardSlots()[i][j].Piece();
			return piece;
		}
		return null;
	}

	/**
	 * get all currently existing pieces in the board
	 * 
	 * @return the array of pieces
	 */
	public Pos[] getAllPiecesPos() {
		ArrayList<Pos> resPos = new ArrayList<Pos>();
		for (int i = 0; i < Board.getRow(); i++) {
			for (int j = 0; j < Board.getCol(); j++) {
				if (Board.getSlot(i, j).Piece() != null) {
					resPos.add(new Pos(i, j));
				}
			}
		}
		if (resPos.size() > 0) {
			Pos[] res = new Pos[resPos.size()];
			for (int i = 0; i < res.length; i++) {
				res[i] = resPos.get(i);
			}
			return res;
		}
		else return null;
	}

	public static int pieceCount(String piecetype) {
		int count = 0;
		for (int x = 0; x < Board.getBoardSlots().length; x++) {
			for (int y = 0; y < Board.getBoardSlots()[0].length; y++) {
				if (Board.getBoardSlots()[x][y] != null
						&& Board.getBoardSlots()[x][y].Piece() != null
						&& Board.getBoardSlots()[x][y].Piece().getPiecetype()
								.equals(piecetype))
					count++;
			}
		}
		return count;
	}

	public static boolean isBoardFull() {
		for (int x = 0; x < Board.getBoardSlots().length; x++) {
			for (int y = 0; y < Board.getBoardSlots()[0].length; y++) {
				if (Board.getBoardSlots()[x][y].available())
					return false;
			}
		}
		return true;
	}

	public static boolean remove(Pos pos, ArrayList<Player> players) {
		Piece piece = Board.getSlot(pos).Piece();
		if (piece == null) {
			return false;
		} else {
			Player owner = piece.owner;
			Board.getSlot(pos).setPiece(null, null);
			// delete the piece from the player
			for (Player p : players) {
				if (p.getId() == owner.getId()) {
					p.removePiece(piece);
				}
			}
			return true;
		}
	}

	public static Piece findNextInRow(Pos pos, int mode) {
		int x = pos.x();
		int y = pos.y();
		Slot currentSlot = Board.getBoardSlots()[x][y];
		if (currentSlot == null || currentSlot.Piece() == null)
			return null;
		if (mode < 0 || mode > 7)
			return null;
		int x_min = 0;
		int y_min = 0;
		int x_max = Board.getBoardSlots().length - 1;
		int y_max = Board.getBoardSlots()[0].length - 1;

		while (x >= x_min && x <= x_max && y >= y_min && y <= y_max) {
			Slot slot = Board.getBoardSlots()[x][y];
			if (slot != null
					&& slot.Piece() != null
					&& slot.Piece().Type == currentSlot.Piece().getPiecetype()
					&& slot.Piece().owner.getId() == currentSlot.Piece().owner
							.getId())
				return Board.getBoardSlots()[x][y].Piece();
			if (mode <= 1 || mode == 7)
				x--;
			if (mode >= 3 && mode <= 5)
				x++;
			if (mode >= 5 && mode <= 7)
				y--;
			if (mode >= 1 && mode <= 3)
				y++;
		}
		return null;
	}

	/**
	 * create an empty two dimension integer table and return.
	 * 
	 * @param row
	 *            number of row in the two dimension table
	 * @param col
	 *            number of column in the two dimension table
	 * @return
	 */
	public int[][] create2DimArray(int row, int col) {
		return new int[row][col];
	}
}
