package edu.columbia.PLT.BGD;

import java.util.Scanner;
/**
 * Main entrance of the program.
 * @author Presenthuang
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to our greatest Tic-Tac-Toe game!");
		System.out.println("This is the start of the game!");
		System.out.println("Player 1 and Player 2 is competing for the 3-in-row!");
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		boolean switcher = true;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true){
			if(switcher){
				System.out.println("Player1 Action:");
				String movement = sc.nextLine();
				String [] spliter = movement.split(" ");
				if(spliter[0].equalsIgnoreCase("add")){
					String []xypos = spliter[1].split(",");
					int posx = Integer.parseInt(xypos[0]);
					int posy = Integer.parseInt(xypos[1]);
					
					if(Functions.add(posx, posy, player1)){
						switcher = false;
						System.out.println("Successfully Added!");
						if(Functions.win(posx, posy, player1)){
							System.out.println("Player 1 wins!");
							break;
						}
					}else {
						System.out.println("Error value!");
					}
				}else {
					System.out.println("Error input!");
				}
			}else {
				System.out.println("Player2 Action:");
				String movement = sc.nextLine();
				String [] spliter = movement.split(" ");
				if(spliter[0].equalsIgnoreCase("add")){
					String []xypos = spliter[1].split(",");
					int posx = Integer.parseInt(xypos[0]);
					int posy = Integer.parseInt(xypos[1]);
					
					if(Functions.add(posx, posy, player2)){
						switcher = true;
						System.out.println("Successfully Added!");
						if(Functions.win(posx, posy, player2)){
							System.out.println("Player 2 wins!");
							break;
						}
					}else {
						System.out.println("Error value!");
					}
				}else {
					System.out.println("Error input!");
				}
			}
		}
	}
}
