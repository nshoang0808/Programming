/**
 * This class creates a Tetris Board
 * 
 * @author VanTrinh
 *
 */

public class TetrisBoard {

	// Total number of columns
	public static final int NUM_COLS = 10;
	// Total number of rows
	public static final int NUM_ROWS = 18;
	// The area surrounding a piece
	public static final int PIECE_AREA = 4;
	// 2D array to hold values of the board
	private boolean[][] blockMatrix;
	// Variable to hold the current Tetris Piece
	private TetrisPiece currentPiece;
	// Array that stores the position of current falling piece
	private int[] currentPieceGridPosition = new int[2];

	public TetrisBoard() {
		// Initiate the array
		blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
		// At the beginning, all values on the board are false
		for (int col = 0; col < NUM_COLS; col++) {
			for (int row = 0; row < NUM_ROWS; row++) {
				blockMatrix[row][col] = false;
			}
		}
		// Add a new piece
		addNewPiece();
	}

	/**
	 * This method adds a new random Tetris piece
	 */
	public void addNewPiece() {
		// CurrentPieceGridPosition[0] stores the current row
		// CurrentPieceGridPosition[1] stores the current column
		// At the beginning, let the piece begin to start appearing at (0,3)
		currentPieceGridPosition[0] = 0;
		currentPieceGridPosition[1] = 3;
		// Get a random number between 0 and 6, corresponding to 7 different
		// Tetris pieces
		int randNum = (int) (Math.random() * (6 - 0));

		// Initialize a new Tetris piece randomly for each random number given
		if (randNum == 0) {
			currentPiece = new TetrisL1();
		} else if (randNum == 1) {
			currentPiece = new TetrisL2();
		} else if (randNum == 2) {
			currentPiece = new TetrisS1();
		} else if (randNum == 3) {
			currentPiece = new TetrisT();
		} else if (randNum == 4) {
			currentPiece = new TetrisSquare();
		} else if (randNum == 5) {
			currentPiece = new TetrisStick();
		} else if (randNum == 6) {
			currentPiece = new TetrisS2();
		}
	}

	/**
	 * Determines if one cell on the board contains a block of Tetris piece This
	 * function will help in drawing the pieces in the console
	 * 
	 * @param row
	 *            the row position of the cell we consider
	 * @param col
	 *            the column position of the cell we consider
	 * @return true if there's a block, false otherwise
	 */
	public boolean hasBlock(int row, int col) {
		// Get the current y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the current x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// Checking the 4x4 area that can possibly contain a Tetris piece
		for (int i = 0; i < PIECE_AREA; i++) {
			for (int j = 0; j < PIECE_AREA; j++) {
				// If x or y coordinate plus the checking area is equal to given
				// row or column
				// And at this position there is a Tetris block, return true
				// (Row and column are switched up so we need to switch i and j
				// in isFilled)
				if ((curRowPos + i == row)
						&& (curColPos + j == col)
						&& currentPiece.isFilled(
								currentPiece.getPieceRotation(), j, i)) {
					return true;
				}
			}
		}// Otherwise return false
		return false;
	}

	/**
	 * This method moves the Tetris piece to the left
	 * 
	 * @return whether the piece moves or doesn't move left
	 */
	public boolean moveLeft() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// If the piece is not null and move is valid
		if (currentPiece != null) {
			if (validMove(currentPiece, currentPiece.getPieceRotation(),
					curRowPos, curColPos - 1)) {
				// Update x coordinate
				currentPieceGridPosition[1]--;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method moves the Tetris piece to the right
	 * 
	 * @return whether the piece moves right or not
	 */
	public boolean moveRight() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// If the piece is not null and move is valid
		if (currentPiece != null) {
			if (validMove(currentPiece, currentPiece.getPieceRotation(),
					curRowPos, curColPos + 1)) {
				// Update x coordinate
				currentPieceGridPosition[1]++;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method moves the Tetris piece downwards
	 * 
	 * @return whether the piece moves down or not
	 */
	public boolean moveDown() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// If the piece is not null and move is valid
		if (currentPiece != null) {
			if (validMove(currentPiece, currentPiece.getPieceRotation(),
					curRowPos + 1, curColPos)) {
				// Update y coordinate
				currentPieceGridPosition[0]++;
				return true;
			}
		}
		return false;
	}

	/**
	 * This method rotates a piece clockwise
	 * 
	 * @return true if a piece is rotated, false otherwise
	 */
	public boolean rotateCW() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// A variable for the attempted rotation
		int nextRotation = 0;

		// If current rotation is smaller than 3 then next rotation is current +
		// 1
		// If current rotation is 3, then the next rotation is 0
		if (currentPiece.getPieceRotation() < 3) {
			nextRotation = currentPiece.getPieceRotation() + 1;
		} else if (currentPiece.getPieceRotation() == 3) {
			nextRotation = 0;
		}

		// Check if the move of next rotation is valid, if yes rotate clockwise
		if (currentPiece != null) {
			if (validMove(currentPiece, nextRotation, curRowPos, curColPos)) {
				currentPiece.rotateCW();
				return true;
			}
		}
		return false;
	}

	/**
	 * This method rotates the piece counterclockwise
	 * 
	 * @return true if rotate counter clockwise
	 */
	public boolean rotateCCW() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// A variable for the attempted rotation
		int nextRotation = 0;

		// If current rotation is 0 then next rotation is 3
		// If current rotation is larger than 0, then the next rotation is
		// current - 1
		if (currentPiece.getPieceRotation() == 0) {
			nextRotation = 3;
		} else if (currentPiece.getPieceRotation() > 0) {
			nextRotation = currentPiece.getPieceRotation() - 1;
		}

		// Check if the move of next rotation is valid, if yes rotate
		// counterclockwise
		if (currentPiece != null) {
			if (validMove(currentPiece, nextRotation, curRowPos, curColPos)) {
				currentPiece.rotateCCW();
				return true;
			}
		}
		return false;
	}

	/**
	 * This method lands a new piece (hard code into the board)
	 */
	public void landPiece() {
		// Get the y coordinate of the piece
		int curRowPos = currentPieceGridPosition[0];
		// Get the x coordinate of the piece
		int curColPos = currentPieceGridPosition[1];
		// Check the 4x4 area of the piece to see where the piece is
		for (int i = 0; i < PIECE_AREA; i++) {
			for (int j = 0; j < PIECE_AREA; j++) {
				if (currentPiece
						.isFilled(currentPiece.getPieceRotation(), j, i)) {
					// Assign true values to the position where piece lands
					blockMatrix[curRowPos + i][curColPos + j] = true;
				}
			}
		}
	}

	/**
	 * This method determines if the move a piece makes is valid or not
	 * 
	 * @param piece
	 *            the Tetris piece
	 * @param rotation
	 *            the rotation of tetris
	 * @param attemptRow
	 *            the row attempted
	 * @param attemptCol
	 *            the column attempted
	 * @return true if move is valid
	 */
	public boolean validMove(TetrisPiece piece, int rotation, int attemptRow,
			int attemptCol) {
		for (int i = 0; i < PIECE_AREA; i++) {
			for (int j = 0; j < PIECE_AREA; j++) {
				// If out of bounds or collides, return true
				if (detectOutOfBounds(piece, rotation, attemptRow, attemptCol)
						|| detectCollisions(piece, rotation, attemptRow,
								attemptCol)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method detects a collision when a piece attempts to move to a new
	 * place
	 * 
	 * @param piece
	 *            the current Tetris piece
	 * @param rotation
	 *            number of rotation of the piece
	 * @param attemptRow
	 *            the row we attempt to move to
	 * @param attemptCol
	 *            the column we attempt to move to
	 * @return true if there is a collision
	 */
	public boolean detectCollisions(TetrisPiece piece, int rotation,
			int attemptRow, int attemptCol) {
		// If the attempted row or column is negative, return true
		if (attemptRow < 0 || attemptCol < 0) {
			return true;
		} else {
			// Check the 4x4 area that possibly contain tetris piece
			for (int i = 0; i < PIECE_AREA; i++) {
				for (int j = 0; j < PIECE_AREA; j++) {
					// If piece is there while the board value is true, then
					// there is a collision
					// (Row and column are switched up so we need to switch i
					// and j in isFilled)
					if (piece.isFilled(rotation, j, i)
							&& blockMatrix[attemptRow + i][attemptCol + j] == true) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method determines if the piece is out of bounds of the board
	 * 
	 * @param piece
	 *            the Tetris Piece
	 * @param rotation
	 *            number of rotation
	 * @param attemptRow
	 *            the row it's attempting to move to
	 * @param attemptCol
	 *            the column it's attempting to move to
	 * @return true if it is out of bounds
	 */
	public boolean detectOutOfBounds(TetrisPiece piece, int rotation,
			int attemptRow, int attemptCol) {
		// If the attempted row or column is negative, return true
		if (attemptRow < 0 || attemptCol < 0) {
			return true;
		} else {
			// Check the 4x4 area that possibly contains tetris piece
			for (int i = 0; i < PIECE_AREA; i++) {
				for (int j = 0; j < PIECE_AREA; j++) {
					// First check if a position is filled or not,
					// If not then it's not eligible for being out of bounds
					// Then, check if attempted row and column are larger than
					// board
					// If yes, the piece is out of bounds
					// (Row and column are switched up so we need to switch i
					// and j in isFilled)
					if (piece.isFilled(rotation, j, i)) {
						if (attemptCol + j >= NUM_COLS
								|| attemptRow + i >= NUM_ROWS) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method checks and removes a full line
	 * 
	 * @return number of formed lines
	 */
	public int numberOfFormedLines() {
		int formedLines = 0;
		for (int row = 0; row < blockMatrix.length; row++) {
			if (fullLine(row) == true) {
				formedLines++;
			}
		}

		for (int row = 0; row < blockMatrix.length; row++) {
			if (fullLine(row) == true) {
				removeLine(row);
			}
		}

		return formedLines;
	}

	/**
	 * This method checks if a line is full
	 * 
	 * @param row
	 *            a row to be checked
	 * @return true if full
	 */
	public boolean fullLine(int row) {
		int counter = 0;
		// At the given row
		for (int col = 0; col < blockMatrix[row].length; col++) {
			// Count amount of true values
			if (blockMatrix[row][col] == true) {
				counter++;
			}
		}
		// If amount of true values are equal to number of columns, return true
		if (counter == NUM_COLS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method copies the above lines down to a removed line
	 * 
	 * @param row
	 *            the row that we are considering
	 */
	public void removeLine(int row) {
		// Copy down from the row above the removed row
		for (int boardRow = row; boardRow < blockMatrix.length && boardRow > 0; boardRow--) {
			for (int col = 0; col < blockMatrix[boardRow].length; col++) {
				blockMatrix[boardRow][col] = blockMatrix[boardRow - 1][col];
			}
		}
		// Give false values to the very first row (row = 0)
		for (int col = 0; col < NUM_COLS; col++) {
			blockMatrix[0][col] = false;
		}
	}

	/**
	 * Gives the position of the current piece
	 * 
	 * @return the top left coordinate of the current piece
	 */
	public int[] getCurrentPieceGridPosition() {

		return currentPieceGridPosition;
	}

	/**
	 * 
	 * @return the current Tetris piece
	 */
	public TetrisPiece getTetrisPiece() {
		return currentPiece;
	}

	/**
	 * Get the tetris board
	 * 
	 * @return 2D boolean array
	 */
	public boolean[][] getBoard() {
		return blockMatrix;
	}

	/**
	 * Get total number of columns of the board
	 * 
	 * @return number of columns
	 */
	public int getNumCols() {
		return NUM_COLS;
	}

	/**
	 * Get total number of rows of the board
	 * 
	 * @return number of rows
	 */
	public int getNumRows() {
		return NUM_ROWS;
	}
}
