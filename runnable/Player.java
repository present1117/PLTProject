import java.util.ArrayList;

/**
 * Player abstract class to implement the player instance.
 * 
 * @author Presenthuang
 * 
 */
public class Player {
	private int numberofpieces;
	private int identifier;
	ArrayList<Piece> pieceList;

	Player(int id) {
		identifier = id;
		numberofpieces = 0;
		pieceList = new ArrayList<Piece>();
	}

	int getId() {
		return identifier;
	}
	
	void setId(int id){
		identifier = id;
	}

	int piecenumber() {
		return numberofpieces;
	}

	boolean addPiece(Piece newpiece) {
		if(newpiece == null)
			return false;
		if (pieceList.add(newpiece)) {
			numberofpieces++;
			return true;
		}
		return false;
	}

	boolean removePiece(Piece p) {
		if(p == null)
			return false;
		Pos pos = p.pos;
		for (Piece one : pieceList) {
			Pos onepos = one.pos;
			if (onepos.x() == pos.x() && onepos.y() == pos.y()) {
				pieceList.remove(one);
				numberofpieces--;
				return true;
			}
		}
		return false;
	}
}
