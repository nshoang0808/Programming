/**
 * This is the square tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisSquare extends TetrisPiece {

	public TetrisSquare() {
		super();
		// Create a square piece
		boolean[][][] theFilledSquares = {
				{ { true, true, false, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } } };
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;
	}
}
