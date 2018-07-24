/**
 * This is the Z tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisS2 extends TetrisPiece {

	public TetrisS2() {
		super();
		// Constructing Z piece
		boolean[][][] theFilledSquares = {
				{ { true, true, false, false }, { false, true, true, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { false, true, false, false }, { true, true, false, false },
						{ true, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { false, true, true, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { false, true, false, false }, { true, true, false, false },
						{ true, false, false, false },
						{ false, false, false, false } } };
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;
	}
}
