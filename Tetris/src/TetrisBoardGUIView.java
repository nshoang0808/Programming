/**
 * A JComponent that draws the board and pieces on the board
 * It is the "View" in the Model-View-Controller
 * @author VanTrinh
 *
 */

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;

public class TetrisBoardGUIView extends JComponent {
	// Number of rows on the board
	public static int BOARD_ROWS = 18;
	// Number of columns on the board
	public static int BOARD_COLS = 10;
	// Top left coordinate of the board
	public static int BOARD_X = 1;
	public static int BOARD_Y = 15;
	// Adjusting the bottom of the board outline to look better
	public static int ADJUSTMENT = 15;
	// The Tetris Board
	private TetrisBoard board;

	/**
	 * Constructor for this class, calling the super class
	 * 
	 * @param b
	 *            the board
	 */
	public TetrisBoardGUIView(TetrisBoard b) {
		super();
		board = b;
	}

	/**
	 * This method paints the graphics - board and pieces
	 * 
	 * @param g
	 *            the graphics
	 */
	public void paint(Graphics g) {
		// Get the height of the frame (can only be done in the paint method,
		// after the JComponent is added to the Controller Panel). Plug in
		// compute block size
		int blockSize = computeBlockSize(this.getHeight());
		// Paint the outline of the board
		paintBoardOutline(g, blockSize);

		// Get the array that represents the board
		boolean[][] arrayBoard = board.getBoard();

		// Paint the blocks where values are true
		for (int boardRow = 0; boardRow < arrayBoard.length; boardRow++) {
			// For each column
			for (int boardCol = 0; boardCol < arrayBoard[boardRow].length; boardCol++) {
				if (board.hasBlock(boardRow, boardCol)
						|| arrayBoard[boardRow][boardCol] == true) {
					// Paint the block at a certain x coordinate and y
					// coordinate
					paintBlock(g, BOARD_X + blockSize * boardCol, BOARD_Y
							+ blockSize * boardRow, blockSize);
				} else {
					// Do nothing
				}
			}
		}

	}

	/**
	 * This method paints the outline of the board
	 * 
	 * @param g
	 *            the graphics
	 * @param blockSize
	 *            the size of a block
	 */
	public void paintBoardOutline(Graphics g, int blockSize) {
		// Set the outline of color black
		g.setColor(Color.BLACK);
		// Draw a rectangle as a block
		g.drawRect(BOARD_X, BOARD_Y, blockSize * BOARD_COLS, blockSize
				* BOARD_ROWS);
	}

	public void paintBlock(Graphics g, int row, int col, int blockSize) {
		// The inside color of the block
		g.setColor(Color.PINK);
		g.fillRect(row, col, blockSize, blockSize);
		// The border of the block
		g.setColor(Color.BLACK);
		g.drawRect(row, col, blockSize, blockSize);

	}

	/**
	 * This method computes certain block size based on the height of the frame
	 * 
	 * @param frameHeight
	 *            the height of frame
	 * @return the block size
	 */
	public int computeBlockSize(int frameHeight) {
		return (frameHeight) / BOARD_ROWS;
	}
}
