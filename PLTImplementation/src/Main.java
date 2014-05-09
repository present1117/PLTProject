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
	//static String[] icons = {"o", "x", "+", "#", "W"};
	//static String[] icons = {"bp.png", "wp.png"};
	static HashMap<String, String>[] iconPool;
	static BoardGUI board = Drawing.drawInitialBoard(); 
	
	static {
		for(int i = 0; i < iconPool.length; i++)
		{
			iconPool[i] = new HashMap<String, String>();
			iconPool[i].put("RED", "red.png");
			iconPool[i].put("YELLOW", "yellow.png");
			iconPool[i].put("GREEN", "green.png");
			iconPool[i].put("BLUE", "blue.png");
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
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			playerid = playerid % playerlist.size();
			System.out.println("Player" + playerid + " Action:");
			String movement = sc.nextLine();
			String[] spliter = movement.split(" ");
			
			if (spliter[0].equalsIgnoreCase("add")) {
				if (spliter.length != 3) {
					System.out.println("Input Length != 3!");
					break;
				}
				else if (!add(spliter[1],spliter[2])) 
					break;
			} else if (spliter[0].equalsIgnoreCase("remove")) {
				if (spliter.length != 2) {
					System.out.println("Input Length != 2");
					break;
				}
				if (!remove(spliter[1]))
					break;
			} else if (spliter[0].equalsIgnoreCase("move")) {
				if (!move(spliter[1]))
					break;
			} else {
				System.out.println("Error input!");
			}
		}
		System.out.println("Game ends!");
	}

	private static boolean add(String addPos, String type) {
		String[] _addPos = addPos.split(",");
		String ptype = "";
		
		if (_addPos.length != 2) {
			System.out.println("Invalid Position!");
			return false;
		}
		try {
			Pos pos = new Pos(Integer.parseInt(_addPos[0]),Integer.parseInt(_addPos[1]));
			for (String t : GameDesigner.pieceType) {
				if (type.equals(t))
					ptype = t;
			}
			if (ptype == "") {
				System.out.println("Missing Piece Type!");
				return false;
			}

			if (Functions.add(pos, playerlist.get(playerid), ptype)) {
				Drawing.drawBoard(board, playerlist, iconPool,ptype,pos.x(),pos.y());
				
				System.out.println("Successfully Added!");
				
				if (Functions.win(pos, playerlist.get(playerid))) {
					System.out.println("Player " + playerid + " wins!");
					return false;
				}
				playerid ++;
			} else {
				System.out.println("Invalid Position!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error input!");
			return false;
		}
		return true;
	}
	private static boolean remove(String removePos) {
		String[] _removePos = removePos.split(",");
		if (_removePos.length != 2) {
			System.out.println("Input Length != 2");
			return false;
		}
		try {
			Pos pos = new Pos(Integer.parseInt(_removePos[0]),Integer.parseInt(_removePos[1]));
			if (Functions.remove(pos, playerlist)) {
				
				Drawing.drawBoard(board, playerlist,iconPool,Functions.getPiece(pos).piecetype(),pos.x(),pos.y() );
				
				System.out.println("Successfully Removed");
				
				if (Functions.win(pos, playerlist.get(playerid))) {
					System.out.println("Player " + playerid + " wins!");
					return false;
				} else {
					System.out.println("Invalid Position!");
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Error input!");
			return false;
		}
		return true;
	}
	private static boolean move(String parameters) {
		String[] split = parameters.split(" ");
		String[] fromPos = split[0].split(",");
		String[] toPos = split[1].split(",");
		return true;
	}
}
