/**
 * This is the stick tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisStick extends TetrisPiece {

	public TetrisStick() {
		super();
		// Create an I piece
		boolean[][][] theFilledSquares = {
				{ { true, false, false, false }, { true, false, false, false },
						{ true, false, false, false },
						{ true, false, false, false } },

				{ { false, false, false, false }, { true, true, true, true },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, false, false, false }, { true, false, false, false },
						{ true, false, false, false },
						{ true, false, false, false } },

				{ { false, false, false, false }, { true, true, true, true },
						{ false, false, false, false },
						{ false, false, false, false } } };
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;
	}

}
