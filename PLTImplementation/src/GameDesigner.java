import java.lang.*;
import java.util.*;

public class GameDesigner {
	static String[] pieceType = { "stone" };
	static int[] pieceNum = { 0 };
	static int boardRow = 3;
	static int boardCol = 3;
	static int playerNum = 2;

	public static boolean add_res(String piece, Pos position) {
		return Functions.isEmpty(position);
	}

	public static boolean win_res(Pos position) {
		if (Functions.numberInRow(position) >= 3) {
			return true;
		} else {
			return false;
		}
	}

	static String[] initPieces = {};
	static int[] initOwner = {};
	static int[][] initPos = {};

	public static boolean move_res(Pos par0, Pos par1) {
		return false;
	}

	public static boolean remove_res(Pos par0) {
		return false;
	}
}
