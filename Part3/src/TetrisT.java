/**
 * This is the T shape tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisT extends TetrisPiece {

	public TetrisT() {
		super();
		// Create a T piece
		boolean[][][] theFilledSquares = {
				{ { true, true, true, false }, { false, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { false, true, false, false }, { true, true, false, false },
						{ false, true, false, false },
						{ false, false, false, false } },

				{ { false, true, false, false }, { true, true, true, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, false, false, false }, { true, true, false, false },
						{ true, false, false, false },
						{ false, false, false, false } } };

		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;
	}
}
