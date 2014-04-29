public class GameDesigner {
<<<<<<< HEAD
	enum pieceType {
		STONE
	};

	static int[] pieceNum = { 0 };
	static int boardRow = 5;
	static int boardCol = 5;
	static int playerNum = 2;

	public static boolean add_res(int[] position) {
		return Functions.isEmpty(position);
	}

	public static boolean win_res(int[] position) {
		if (Functions.numberInRow(position) >= 5) {
=======
	static String[] TYPE = { "RED", "YELLOW", "GREEN", "BLUE" };

	static int[] pieceNum = { 0, 0, 0, 0 };
	static int boardRow = 8;
	static int boardCol = 8;
	static int playerNum = 1;

	public static boolean add_res(String piece, Pos position) {
		String PIECE_TYPE = piece;

		if (PIECE_TYPE == "RED" || PIECE_TYPE == "YELLOW") {
			return Functions.isEmpty(position);
		}

		if (PIECE_TYPE == "GREEN") {
			return Functions.isEmpty(position)
					&& Functions.getPieceType(Functions.getPiece(position.x(),
							position.y() - 1)) == "RED"
					&& Functions.getPieceType(Functions.getPiece(position.x(),
							position.y() + 1)) == "RED"
					&& Functions.getPieceType(Functions.getPiece(
							position.x() - 1, position.y())) == "RED"
					&& Functions.getPieceType(Functions.getPiece(
							position.x() + 1, position.y())) == "RED";
		}

		if (PIECE_TYPE == "BLUE") {
			return Functions.isEmpty(position)
					&& Functions.getPieceType(Functions.getPiece(position.x(),
							position.y() - 1)) == "RED"
					&& Functions.getPieceType(Functions.getPiece(
							position.x() - 1, position.y())) == "RED"
					&& Functions.getPieceType(Functions.getPiece(position.x(),
							position.y() + 1)) == "YELLOW"
					&& Functions.getPieceType(Functions.getPiece(
							position.x() + 1, position.y())) == "YELLOW";
		}
		return false;
	}

	public static boolean win_res(int[] position) {
		if (Functions.pieceCount("GREEN") + Functions.pieceCount("BLUE") == 15) {
>>>>>>> FETCH_HEAD
			return true;
		} else {
			return false;
		}
	}

<<<<<<< HEAD
	public static boolean move_res(int[] par1, int[] par2) {
		return true;
	}

	public static boolean remove_res(int[] par1) {
		return true;
	}
}
=======
	public static boolean move_res(int[] par0, int[] par1) {
		return true;
	}

	public static boolean remove_res(int[] par0) {
		return true;
	}
}
>>>>>>> FETCH_HEAD
