package edu.columbia.PLT.BGD;

import java.util.ArrayList;

/**
 * Player abstract class to implement the player instance.
 * @author Presenthuang
 *
 */
public class Player {
	private int numberofpieces;
	private int identifier;
	ArrayList<Piece> pieceList;

	Player(int id){
		identifier = id;
		numberofpieces = 0;
		pieceList = new ArrayList<Piece>();
	}
	
	int getId(){
		return identifier;
	}
	
	int piecenumber(){
		return numberofpieces;
	}
	
	boolean addPiece(Piece newpiece){
		if(pieceList.add(newpiece)){
			numberofpieces++;
			return true;
		}
		return false;	
	}
}
