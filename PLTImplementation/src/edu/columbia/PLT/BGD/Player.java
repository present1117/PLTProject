package edu.columbia.PLT.BGD;

import java.util.ArrayList;

/**
 * Player abstract class to implement the player instance.
 * @author Presenthuang
 *
 */
public class Player {
	int numberofpieces;
	int identifier;
	ArrayList<Piece> pieceList;
	
	Player() {
		// TODO Auto-generated constructor stub
	}
	
	Player(int id){
		identifier = id;
		numberofpieces = 0;
		pieceList = new ArrayList<Piece>();
	}
	
	
}
