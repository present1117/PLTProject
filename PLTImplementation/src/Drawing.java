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
	public static void drawBoard(BoardGUI board, String action, Pos pos)
	{
		int numOfRows = Board.getRow();
		int numOfColumns = Board.getCol();
		
		for(int i = 0; i < numOfRows; i++)
		{
			for(int j = 0; j < numOfColumns; j++)
			{
				try{
					if(action.equalsIgnoreCase("remove") && (pos.x() == i && pos.y() == j))
						remove(board, i+""+j);
					else
						add(board, i+""+j, Board.getBoardSlots()[i][j].Piece().Type + ".png");
				}
				catch(NullPointerException e)
				{
					
				}		
			}	
		}
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
	
	public static void remove(BoardGUI board, String position)
	{
		board.removePiece(position);
	}
}
