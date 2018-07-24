/**
 * This class is in charge of a lot of game logic
 * 
 * @author VanTrinh
 *
 */

public class TetrisGame {

	public static final int RIGHT = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static final int CW = 4;
	public static final int CCW = 5;

	// A variable to hold the Tetris Board
	private TetrisBoard board;
	// A variable to hold the number of lines
	private int numLine = 0;
	// A variable to check number of Tetrises
	private int numTetrises = 0;

	public TetrisGame() {
		board = new TetrisBoard();
	}

	/**
	 * This method attempts to move the piece on the board
	 * 
	 * @param moveType
	 *            pass in the desired move type
	 */
	public void attemptMove(int moveType) {
		boolean move = true;

		switch (moveType) {
		case LEFT:
			move = board.moveLeft();
			break;
		case RIGHT:
			move = board.moveRight();
			break;
		case DOWN:
			move = board.moveDown();
			// If attempting to move down and the move is not valid (move =
			// false)
			// Then end round - land piece and add a new piece
			if (!move) {
				endRound();
			}
			break;
		case CW:
			move = board.rotateCW();
			break;
		case CCW:
			move = board.rotateCCW();
			break;
		default:
			System.out.println("I ran out of cases");
			break;
		}

	}

	/**
	 * This method lands piece, check number of lines cleared and add a new
	 * piece
	 */
	public void endRound() {
		// Land the piece
		board.landPiece();
		// Get the number of lines formed
		numLine = numLine + board.numberOfFormedLines();
		// If number of lines divisible by 4, a Tetris is formed
		if (board.numberOfFormedLines() == 4) {
			numTetrises++;
		} else {

		}
		// Add a new piece to board
		board.addNewPiece();
	}

	/**
	 * This method returns the number of lines cleared
	 * 
	 * @return numLine
	 */
	public int getNumLines() {
		return numLine;
	}

	/**
	 * Return every time 4 lines are cleared in a row
	 * 
	 * @return numTetrises
	 */
	public int getNumTetrises() {
		return numTetrises;
	}

	/**
	 * Provide the Tetris board
	 * 
	 * @return the board
	 */
	public TetrisBoard getTetrisBoard() {
		return board;
	}
}
