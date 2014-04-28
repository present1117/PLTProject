public class GameDesigner {
	enum pieceType {
		RED, YELLOW, GREEN, BLUE
	};

	static int[] pieceNum = { 0 };
	static int boardRow = 8;
	static int boardCol = 8;
	static int playerNum = 1;

	public static boolean add_res(int[] position) {
		if(Board.boardslots[position[0]][position[1]].Piece() == null){
			return false;
		}
		return Functions.isEmpty(position);
	}

	public static boolean win_res(int[] position) {
		if (Functions.numberInRow(position) >= 5) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean move_res(int[] par1, int[] par2) {
		return true;
	}

	public static boolean remove_res(int[] par1) {
		return true;
	}
}