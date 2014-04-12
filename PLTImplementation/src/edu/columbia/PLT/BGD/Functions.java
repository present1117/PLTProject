package edu.columbia.PLT.BGD;
/**
 * Abstract class for all functions in the program
 * as well all the built-in functions
 * @author Presenthuang
 *
 */

public class Functions {
	public static boolean add(int posx, int posy, Player currentowner) {
		if(isEmpty(posx, posy)){
			Board.boardSlots[posx][posy] = new Slot();
			Board.boardSlots[posx][posy].setPiece(new Piece(currentowner));
			Board.boardSlots[posx][posy].setPlayer(currentowner);
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean win(int posx, int posy, Player currentowner) {
		if(numberInRow(posx, posy) >= 3){
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean isEmpty(int posx, int posy) {
		Slot currentSlot = Board.boardSlots[posx][posy];
		if(currentSlot == null){
			return true;
		}else {
			return false;
		}
	}
	
	public static int numberInRow(int posx, int posy) {
		Slot currentSlot = Board.boardSlots[posx][posy];
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
		for(int i = posy+1; i < Board.parcolumn; ++i){
			if(Board.boardSlots[posx][i] == null)
				break;
			if(Board.boardSlots[posx][i].getPlayer() == currentSlot.getPlayer()){
				total++;
			}else {
				break;
			}
		}
		for(int i = posy - 1; i >= 0; --i){
			if(Board.boardSlots[posx][i] == null)
				break;
			if(Board.boardSlots[posx][i].getPlayer() == currentSlot.getPlayer()){
				total++;
			}else {
				break;
			}
		}
		
		return total;
	}
	
	private static int CountVertical(int posx, int posy, Slot currentSlot){
		int total = 1;
		for(int i = posx+1; i < Board.parrow; ++i){
			if(Board.boardSlots[i][posy] == null)
				break;
			if(Board.boardSlots[i][posy].getPlayer() == currentSlot.getPlayer()){
				total++;
			}else {
				break;
			}
		}
		for(int i = posx - 1; i >= 0; --i){
			if(Board.boardSlots[i][posy] == null)
				break;
			if(Board.boardSlots[i][posy].getPlayer() == currentSlot.getPlayer()){
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
		while(i >= 0 && i < Board.parrow && j >=0 && j < Board.parcolumn){
			if(Board.boardSlots[i][j] == null)
				break;
			if(Board.boardSlots[i][j].getPlayer() == currentSlot.getPlayer()){
				total1++;
			}
			i-=1;
			j-=1;
		}
		i = posx;
		j = posy;
		i +=1;
		j +=1;
		while(i >= 0 && i < Board.parrow && j >=0 && j < Board.parcolumn){
			if(Board.boardSlots[i][j] == null)
				break;
			if(Board.boardSlots[i][j].getPlayer() == currentSlot.getPlayer()){
				total1++;
			}
			i+=1;
			j+=1;
		}
		
		i = posx;
		j = posy;
		i-=1;
		j+=1;
		while(i >= 0 && i < Board.parrow && j >=0 && j < Board.parcolumn){
			if(Board.boardSlots[i][j] == null)
				break;
			if(Board.boardSlots[i][j].getPlayer() == currentSlot.getPlayer()){
				total2++;
			}
			i-=1;
			j+=1;
		}
		
		i = posx;
		j = posy;
		i+=1;
		j-=1;
		while(i >= 0 && i < Board.parrow && j >=0 && j < Board.parcolumn){
			if(Board.boardSlots[i][j] == null)
				break;
			if(Board.boardSlots[i][j].getPlayer() == currentSlot.getPlayer()){
				total2++;
			}
			i+=1;
			j-=1;
		}
		
		return total1 > total2 ? total1:total2;
	}
}
