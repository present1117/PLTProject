import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Defination of drawing, to draw the graph of the chess board just in time.
 * 
 * @author Presenthuang
 * 
 */
public class Drawing {
	public static void drawBoard(ArrayList<Player> pl, String[] icons) {
		Slot[][] slots = Board.boardslots;
		int numOfRows = Board.row();
		int numOfColumns = Board.col();

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				try {
					// if(slots[i][j].Player().getId() == 0)
					// System.out.print("o ");
					// else
					// System.out.print("x ");
					System.out.print(icons[slots[i][j].Player().getId()] + " ");
				} catch (NullPointerException e) {
					System.out.print(". ");
				}

			}
			System.out.println("");
		}
	}

	public static void add(BoardGUI board, String position, String iconName)
	{
		//add pieces to board
		board.addPiece(new ImageIcon(iconName), position);

	}
}
