import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Main entrance of the program.
 * 
 * @author Presenthuang
 * 
 */

public class Main {
	static ArrayList<Player> playerlist = new ArrayList<Player>();
	static HashMap<Integer, HashMap<String, String>> iconPool = new HashMap<Integer, HashMap<String, String>>();
	static BoardGUI board = Drawing.drawEmptyBoard(); 
	
	static final int FALSE = 0;
	static final int SUCCESS = 1;
	static final int WIN = 2;

	static {
		for(int i = 0; i < GameDesigner.pieceNum.length; i++)
		{
			iconPool.put(i, new HashMap<String, String>());
			for (int j = 0; j < GameDesigner.pieceType.length; j++) {
				iconPool.get(i).put(GameDesigner.pieceType[j], GameDesigner.pieceType[j]+".png");
			}
		}
	}
	
	static int playerid = 0;
	
	public static void main(String[] args) {

		System.out.println("Welcome to our greatest Board game!");
		System.out.println("This is the start of the game!");
		
		for (int i = 0; i < GameDesigner.playerNum; ++i) {
			Player newplayer = new Player(i);
			playerlist.add(newplayer);
		}

		
		for (int i = 0; i < GameDesigner.initOwner.length; i ++) {
			Pos pos = new Pos(GameDesigner.initPos[i][0],GameDesigner.initPos[i][1]);
			Player currentPlayer = playerlist.get(GameDesigner.initOwner[i]);
			Board.initSlot(pos);
			Board.getSlot(pos).setPiece(new Piece(currentPlayer, new Pos(pos), GameDesigner.initPieces[i]), currentPlayer);
			Drawing.drawBoard(board, "add", pos, playerlist.size());
		}
		
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		boolean flag = false;

		
		while (true) {
			playerid = playerid % playerlist.size();
			System.out.println("Player" + playerid + " Action:");
			String movement = sc.nextLine();
			String[] spliter = movement.split(" ");
			
			if (spliter[0].equalsIgnoreCase("add")) {
				if (spliter.length != 3) {
					System.out.println("Input Length != 3!");
				}
				else{
					switch(add(spliter[1],spliter[2])) {
						case FALSE:
							System.out.println("Failed to Add!");
							break;
						case WIN:
							System.out.println("Player " + playerid + " wins!");
							flag = true;
							break;
						case SUCCESS:
							System.out.println("Successfully Added!");
							break;
					}
				} 
			} else if (spliter[0].equalsIgnoreCase("remove")) {
				if (spliter.length != 2) {
					System.out.println("Input Length != 2");
				}
				switch(remove(spliter[1])) {
						case FALSE:
							System.out.println("Failed to Remove!");
							break;
						case WIN:
							flag = true;
							System.out.println("Player " + playerid + " wins!");
							break;
						case SUCCESS:
							System.out.println("Successfully Removed");
							break;
				}
			} else if (spliter[0].equalsIgnoreCase("move")) {
				switch(move(spliter[1])) {
						case FALSE:
							System.out.println("Failed to Move!");
							break;
						case WIN:
							System.out.println("Player " + playerid + " wins!");
							flag = true;
							break;
						case SUCCESS:
							System.out.println("Successfully Moved");
							break;
				}
			} else {
				System.out.println("Error input!");
			}
			if (flag) break;
		}
		
		System.out.println("Game ends!");
	}

	private static int add(String addPos, String type) {
		String[] _addPos = addPos.split(",");
		String ptype = "";
		
		if (_addPos.length != 2) {
			System.out.println("Invalid Length!");
			return FALSE;
		}
		try {
			Pos pos = new Pos(Integer.parseInt(_addPos[0]),Integer.parseInt(_addPos[1]));
			for (String t : GameDesigner.pieceType) {
				if (type.equalsIgnoreCase(t))
					ptype = t;
			}
			if (ptype == "") {
				System.out.println("Invalid Piece Type!");
				return FALSE;
			}

			if (Functions.add(pos, playerlist.get(playerid), ptype)) {
				Drawing.drawBoard(board,"add", pos, playerlist.size());
				if (Functions.win(pos, playerlist.get(playerid)))
					return WIN;
				playerid ++;
			} else {
				return FALSE;
			}
		} catch (Exception e) {
			return FALSE;
		}
		return SUCCESS;
	}
	private static int remove(String removePos) {
		String[] _removePos = removePos.split(",");
		if (_removePos.length != 2) {
			System.out.println("Invalid Length");
			return FALSE;
		}
		try {
			Pos pos = new Pos(Integer.parseInt(_removePos[0]),Integer.parseInt(_removePos[1]));
			if (Functions.remove(pos, playerlist)) {
				Drawing.drawBoard(board,"remove",pos,playerlist.size());
				if (Functions.win(pos, playerlist.get(playerid)))
					return WIN;
				playerid ++; 
			} else {
				return FALSE;
			}
		} catch (Exception e) {
			return FALSE;
		}
		return SUCCESS;
	}
	private static int move(String parameters) {
		String[] split = parameters.split(" ");
		String[] fromPos = split[0].split(",");
		String[] toPos = split[1].split(",");
		try {
			Pos from = new Pos(Integer.parseInt(fromPos[0]),Integer.parseInt(fromPos[1]));
			Pos to = new Pos(Integer.parseInt(toPos[0]),Integer.parseInt(toPos[1]));
			if (Functions.move(from,to,playerlist)) {
				if (Functions.win(to, playerlist.get(playerid))) {
						return WIN;
				} else {
					playerid ++;
					return SUCCESS;
				}
			}
			else return FALSE;
		} catch (Exception e) {
			return FALSE;
		}
		
	}
}
