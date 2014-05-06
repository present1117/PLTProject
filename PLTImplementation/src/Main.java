import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main entrance of the program.
 * 
 * @author Presenthuang
 * 
 */

public class Main {
	static ArrayList<Player> playerlist = new ArrayList<Player>();
	static String[] icons = { "../bp.png", "../wp.png" };
	static private BoardGUI board = Drawing.drawInitialBoard(); 
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
				if (!add(spliter[1])) 
					break;
			} else if (spliter[0].equalsIgnoreCase("remove")) {
				if (!remove(spliter[1]))
					break;
			} else {
				System.out.println("Error input!");
			}
		}
		System.out.println("Game ends!");
	}

	private static boolean add(String parameters) {
		String[] xypos = parameters.split(",");
		if (xypos.length != 3) {
			System.out.println("Input length != 3!");
			return false;
		}
		try {
			Pos pos = new Pos(Integer.parseInt(xypos[0]),Integer.parseInt(xypos[1]));
			String ptype = "";
			for (String t : GameDesigner.pieceType) {
				if (xypos[2].equals(t))
					ptype = t;
			}
			if (ptype == "") {
				System.out.println("Missing Piece Type!");
				return false;
			}

			if (Functions.add(pos, playerlist.get(playerid), ptype)) {
				Drawing.drawBoard(board, playerlist, icons);
				
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
	private static boolean remove(String parameters) {
		String[] pos = parameters.split(",");
		if (pos.length != 2) {
			System.out.println("Input Length != 2");
			return false;
		}
		try {
			int posx = Integer.parseInt(pos[0]);
			int posy = Integer.parseInt(pos[1]);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
}
