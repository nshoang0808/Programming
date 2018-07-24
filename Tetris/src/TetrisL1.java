/**
 * This is the L tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisL1 extends TetrisPiece {

	public TetrisL1() {
		super();
		// Constructing L piece
		boolean[][][] theFilledSquares = {
				{ { true, false, false, false }, { true, false, false, false },
						{ true, true, false, false },
						{ false, false, false, false } },

				{ { true, true, true, false }, { true, false, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { false, true, false, false },
						{ false, true, false, false },
						{ false, false, false, false } },

				{ { false, false, true, false }, { true, true, true, false },
						{ false, false, false, false },
						{ false, false, false, false } }

		};
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;
	}
}
