/**
 * This class creates a Tetris Piece
 * 
 * @author VanTrinh
 *
 */
public class TetrisPiece {

	// The 3D array that represents each piece
	protected boolean[][][] filledSquares;

	// The number that corresponds to the rotation of each piece
	protected int pieceRotation = 0;

	/**
	 * Constructor for Tetris super class is empty
	 */
	public TetrisPiece() {

	}

	/**
	 * This method rotates piece clockwise
	 */
	public void rotateCW() {
		// If current rotation is smaller than 3, increment by one
		if (pieceRotation < 3) {
			pieceRotation = pieceRotation + 1;
			// If current rotation is 3, set it back to 0
		} else if (pieceRotation == 3) {
			pieceRotation = pieceRotation - 3;
		}
	}

	/**
	 * This method rotates piece counterclockwise
	 */
	public void rotateCCW() {
		// If current rotation is 0, set it to 3
		if (pieceRotation == 0) {
			pieceRotation = pieceRotation + 3;
			// If current rotation is larget than 0, decrement by one
		} else if (pieceRotation > 0) {
			pieceRotation = pieceRotation - 1;
		}
	}

	/**
	 * This method determine if a certain position on the Tetris piece is true
	 * or not
	 * 
	 * @param rotation
	 *            the number of rotation
	 * @param row
	 *            a row of the Tetris piece
	 * @param col
	 *            a column of the Tetris piece
	 * @return true if filled, false otherwise
	 */
	public boolean isFilled(int rotation, int row, int col) {
		// If value at this position is a true value, return true
		// Here first dimension is rotation, second dimension is column
		// And third dimension is row
		if (filledSquares[rotation][col][row] == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Provides current piece rotation
	 * 
	 * @return the piece rotation
	 */
	public int getPieceRotation() {
		return pieceRotation;
	}
}
