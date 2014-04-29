public class GameDesigner {
	public enum TYPE {
		RED(0), YELLOW(1), GREEN(2), BLUE(3);
		private final int value;
		
        private TYPE(final int newValue) {
            value = newValue;
        }
        public int getValue() { return value; }
	};
	

	static int[] pieceNum = { 0, 0, 0, 0 };
	static int boardRow = 8;
	static int boardCol = 8;
	static int playerNum = 1;

	public static boolean add_res (int piece,int[] position){
		int PIECE_TYPE=piece;
		
		if(PIECE_TYPE==TYPE.RED.getValue()||PIECE_TYPE==TYPE.YELLOW.getValue())
		{
			return Functions.isEmpty(position);
		}
		
		if(PIECE_TYPE==TYPE.GREEN.getValue())
		{
			return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position[0], position[1]-1))==TYPE.RED.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0], position[1]+1))==TYPE.RED.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0]-1, position[1]))==TYPE.RED.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0]+1, position[1]))==TYPE.RED.getValue();
		}
		
		if(PIECE_TYPE==TYPE.BLUE.getValue())
		{
			return Functions.isEmpty(position)&&Functions.getPieceType(Functions.getPiece(position[0], position[1]-1))==TYPE.RED.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0]-1, position[1]))==TYPE.RED.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0], position[1]+1))==TYPE.YELLOW.getValue()
				&&Functions.getPieceType(Functions.getPiece(position[0]+1, position[1]))==TYPE.YELLOW.getValue();
		}
		return false;
	}

	public static boolean win_res(int[] position) {
		if (Functions.pieceCount(TYPE.GREEN.getValue()) + Functions.pieceCount(TYPE.BLUE.getValue()) == 15) {
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
