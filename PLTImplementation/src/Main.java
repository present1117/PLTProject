import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.Templates;
/**
 * Main entrance of the program.
 * @author Presenthuang
 *
 */

public class Main {
	static ArrayList<Player> playerlist = new ArrayList<Player>();
	//static String[] icons = {"o", "x", "+", "#", "W"};
	static String[] icons = {"/Users/xingyuwang/Desktop/bp.png", "/Users/xingyuwang/Desktop/wp.png"};
	public static void main(String[] args) {
		BoardGUI board = Drawing.drawInitialBoard();
		System.out.println("Welcome to our greatest Tic-Tac-Toe game!");
		System.out.println("This is the start of the game!");
		System.out.println("Player 0 and Player 1 is competing for the 3-in-row!");
		System.out.println("Input roles: \"add posx,posy\"");
		
		for(int i = 0; i < GameDesigner.playerNum; ++i){
			Player newplayer = new Player(i);
			playerlist.add(newplayer);
		}
		int switcher = 0;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true){
			switcher = switcher % playerlist.size();
			System.out.println("Player" + switcher + " Action:");
			String movement = sc.nextLine();
			String [] spliter = movement.split(" ");
			if(spliter[0].equalsIgnoreCase("add")){
				String []xypos = spliter[1].split(",");
				int posx = Integer.parseInt(xypos[0]);
				int posy = Integer.parseInt(xypos[1]);

				if(Functions.add(posx, posy, playerlist.get(switcher))){
					Drawing.drawBoard(board, playerlist, icons);
					System.out.println("Successfully Added!");
					if(Functions.win(posx, posy, playerlist.get(switcher))){
						System.out.println("Player "+switcher+" wins!");
						break;
					}
					switcher++;
				}else {
					System.out.println("Error value!");
				}
			}else {
				System.out.println("Error input!");
			}
		}
		System.out.println("Game ends!");
	}
}
