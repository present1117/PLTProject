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
	static HashMap<String, String>[] iconPool = new HashMap[GameDesigner.playerNum];
	
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
	
	
	public static void main(String[] args) {
		BoardGUI board = Drawing.drawInitialBoard();
		System.out.println("Welcome to our greatest Board game!");
		System.out.println("This is the start of the game!");
		// System.out.println("Player 0 and Player 1 is competing for the 3-in-row!");
		// System.out.println("Input roles: \"add posx,posy\"");

		for (int i = 0; i < GameDesigner.playerNum; ++i) {
			Player newplayer = new Player(i);
			playerlist.add(newplayer);
		}
		int playerid = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (true) {
			playerid = playerid % playerlist.size();
			System.out.println("Player" + playerid + " Action:");
			String movement = sc.nextLine();
			String[] spliter = movement.split(" ");
			if (spliter[0].equalsIgnoreCase("add")) {
				String[] xypos = spliter[1].split(",");
				if(xypos.length < 3){
					System.out.println("Error input!");
					break;
				}
				try{
					int posx = Integer.parseInt(xypos[0]);
					int posy = Integer.parseInt(xypos[1]);	
					String ptype  = "";
					for(String t : GameDesigner.pieceType){
						if(xypos[2].equals(t))
							ptype = t;
					}
					if(ptype == ""){
						System.out.println("Error input!");
						break;
					}
					
					if (Functions.add(posx, posy, playerlist.get(playerid), ptype)) {
						Drawing.drawBoard(board, playerlist, iconPool, ptype, posx, posy);
						System.out.println("Successfully Added!");
						if (Functions.win(posx, posy, playerlist.get(playerid))) {
							System.out.println("Player " + playerid + " wins!");
							break;
						}
						playerid++;
					} else {
						System.out.println("Error value!");
					}
				}catch(Exception e){
					System.out.println("Error input!");
					break;
				}
			} else {
				System.out.println("Error input!");
			}
		}
		System.out.println("Game ends!");
	}
}
