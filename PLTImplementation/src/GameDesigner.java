public class GameDesigner {
	static String RED = "RED";
	static String YELLOW = "YELLOW";
	static String GREEN = "GREEN";
	static String BLUE = "BLUE";

	static int[] pieceNum = { 0, 0, 0, 0 };
	static int boardRow = 8;
	static int boardCol = 8;
	static int playerNum = 1;

	public static boolean add_res (String piece,Pos position){
		String PIECE_TYPE= piece;
		
		if(PIECE_TYPE.equals(RED)||PIECE_TYPE.equals(YELLOW))
		{
			return Functions.isEmpty(position);
		}
		
		if(PIECE_TYPE.equals(GREEN))
		{
			return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()-1)).equals(RED)
				&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()+1)).equals(RED)
				&&Functions.getPieceType(Functions.getPiece(position.x()-1, position.y())).equals(RED)
				&&Functions.getPieceType(Functions.getPiece(position.x()+1, position.y())).equals(RED);
		}
		
		if(PIECE_TYPE.equals(BLUE))
		{
			return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()-1)).equals(RED)
				&&Functions.getPieceType(Functions.getPiece(position.x()-1, position.y())).equals(RED)
				&&Functions.getPieceType(Functions.getPiece(position.x(), position.y()+1)).equals(YELLOW)
				&&Functions.getPieceType(Functions.getPiece(position.x()+1, position.y())).equals(YELLOW);
		}
		return false;
	}

	public static boolean win_res(int[] position) {
		if (Functions.pieceCount(GREEN) + Functions.pieceCount(BLUE) == 15) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean move_res(int[] par0, int[] par1) {
		return true;
	}

	public static boolean remove_res(int[] par0) {
		return true;
	}
}
