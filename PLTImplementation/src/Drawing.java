import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 * Defination of drawing, to draw the graph of the chess board just in time.
 * @author Presenthuang
 *
 */
public class Drawing {
	public static void drawBoard(BoardGUI board, ArrayList<Player> pl, HashMap[] iconPool, String pieceType, int posx, int posy)
	{
		Slot[][] slots = Board.boardslots;
		//int numOfRows = Board.row();
		//int numOfColumns = Board.col();
		
//		for(int i = 0; i < numOfRows; i++)
//		{
//			for(int j = 0; j < numOfColumns; j++)
//			{
//				try{
//					//System.out.print(icons[slots[i][j].Player().getId()] + " ");
//					add(board, i+""+j, iconPool[slots[i][j].Player().getId()].get(pieceType).toString());
//					
//				}
//				catch(NullPointerException e)
//				{
//					
//				}		
//			}	
//		}
		
		add(board, posx+""+posy, iconPool[slots[posx][posy].Player().getId()].get(pieceType).toString());
		
	}

	
	public static BoardGUI drawInitialBoard()
	{
		try{
			Image whiteBlock = ImageIO.read(new File("wood.jpg"));
			BoardGUI board = new BoardGUI(whiteBlock);
			return board;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		



	}

	public static void add(BoardGUI board, String position, String iconName)
	{
		//add pieces to board
		board.addPiece(new ImageIcon(iconName), position);

	}
}
