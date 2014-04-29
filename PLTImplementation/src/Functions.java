import java.util.ArrayList;

/**
 * class for all functions in the program
 * as well all the built-in functions
 * @author Presenthuang
 *
 */

public class Functions {
	public static boolean add(int posx, int posy, Player currentowner) {
		if(GameDesigner.add_res(new int [] {posx, posy})){
			Board.boardslots[posx][posy] = new Slot();
			Board.boardslots[posx][posy].setPiece(new Piece(currentowner,new Pos(posx, posy)), currentowner);
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean win(int posx, int posy, Player currentowner) {
		if(GameDesigner.win_res(new int [] {posx, posy})){
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isEmpty(int [] position) {
		if(position.length < 2)
			return false;
		int posx = position[0];
		int posy = position[1];
		if(posx >= Board.boardslots.length || posy >= Board.boardslots[0].length || posx < 0 || posy < 0)
			return false;
		Slot currentSlot = Board.boardslots[posx][posy];
		if(currentSlot == null){
			return true;
		}else {
			return false;
		}
	}
	
	public static int numberInRow(int [] position) {
		if(position.length < 2)
			return 0;
		int posx = position[0];
		int posy = position[1];
		Slot currentSlot = Board.boardslots[posx][posy];
		if(currentSlot == null){
			return 0;
		}
		//check horizontal line
		//check vertical line
		//check diagonal line
		int a = CountDiagonal(posx, posy, currentSlot);
		int b = CountHorizontal(posx, posy, currentSlot);
		int c = CountVertical(posx, posy, currentSlot);
		
		return Math.max(a, Math.max(b, c));
	}
	
	private static int CountHorizontal(int posx, int posy, Slot currentSlot){
		int total = 1;
		for(int i = posy+1; i < Board.col(); ++i){
			if(Board.boardslots[posx][i] == null)
				break;
			if(Board.boardslots[posx][i].Player() == currentSlot.Player()){
				total++;
			}else {
				break;
			}
		}
		for(int i = posy - 1; i >= 0; --i){
			if(Board.boardslots[posx][i] == null)
				break;
			if(Board.boardslots[posx][i].Player() == currentSlot.Player()){
				total++;
			}else {
				break;
			}
		}
		
		return total;
	}
	
	private static int CountVertical(int posx, int posy, Slot currentSlot){
		int total = 1;
		for(int i = posx+1; i < Board.row(); ++i){
			if(Board.boardslots[i][posy] == null)
				break;
			if(Board.boardslots[i][posy].Player() == currentSlot.Player()){
				total++;
			}else {
				break;
			}
		}
		for(int i = posx - 1; i >= 0; --i){
			if(Board.boardslots[i][posy] == null)
				break;
			if(Board.boardslots[i][posy].Player() == currentSlot.Player()){
				total++;
			}else {
				break;
			}
		}
		
		return total;
	}
	
	private static int CountDiagonal(int posx, int posy, Slot currentSlot){
		int i = posx;
		int j = posy;
		int total1 = 1;
		int total2 = 1;
		
		i -= 1;
		j -= 1;
		while(i >= 0 && i < Board.row() && j >=0 && j < Board.col()){
			if(Board.boardslots[i][j] == null)
				break;
			if(Board.boardslots[i][j].Player() == currentSlot.Player()){
				total1++;
			}
			i-=1;
			j-=1;
		}
		i = posx;
		j = posy;
		i +=1;
		j +=1;
		while(i >= 0 && i < Board.row() && j >=0 && j < Board.col()){
			if(Board.boardslots[i][j] == null)
				break;
			if(Board.boardslots[i][j].Player() == currentSlot.Player()){
				total1++;
			}
			i+=1;
			j+=1;
		}
		
		i = posx;
		j = posy;
		i-=1;
		j+=1;
		while(i >= 0 && i < Board.row() && j >=0 && j < Board.col()){
			if(Board.boardslots[i][j] == null)
				break;
			if(Board.boardslots[i][j].Player() == currentSlot.Player()){
				total2++;
			}
			i-=1;
			j+=1;
		}
		
		i = posx;
		j = posy;
		i+=1;
		j-=1;
		while(i >= 0 && i < Board.row() && j >=0 && j < Board.col()){
			if(Board.boardslots[i][j] == null)
				break;
			if(Board.boardslots[i][j].Player() == currentSlot.Player()){
				total2++;
			}
			i+=1;
			j-=1;
		}
		
		return total1 > total2 ? total1:total2;
	}
	
	public static int getPieceType(Piece p){
		return p.piecetype();
	}
	
	public static Player getPiecePlayer(Piece p){
		return p.owner;
	}
	
	public static Pos getPiecePos(Piece p){
		return p.pos;
	} 
	
	public static Piece getPiece(Pos po){
		Piece piece = Board.boardslots[po.x()][po.y()].Piece();
		return piece;
	}
	
	public static int pieceCount(){
		return 0;
		
	}
	
	public static boolean isBoardFull(){
		return false;
		
	}
	
	
	public static boolean remove(Pos po, ArrayList<Player> players){
		Piece piece = Board.boardslots[po.x()][po.y()].Piece();
		if(piece == null){
			return false;
		}else {
			Player owner = piece.owner;
			Board.boardslots[po.x()][po.y()].setPiece(null,null);
			//delete the piece from the player
			for(Player p : players){
				if(p.getId() == owner.getId()){
					p.removePiece(piece);
				}
			}
			return true;
		}
	}

	void getPiecefromPlayer(){
		
	}
	
	void findNextInRow(){
		
	}
	
	
}
