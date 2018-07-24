/**
 * This is the J tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisL2 extends TetrisPiece {

	public TetrisL2() {
		super();
		// Constructing reverse L piece
		boolean[][][] theFilledSquares = {
				{ { false, true, false, false }, { false, true, false, false },
						{ true, true, false, false },
						{ false, false, false, false } },

				{ { true, false, false, false }, { true, true, true, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, true, false, false }, { true, false, false, false },
						{ true, false, false, false },
						{ false, false, false, false } },

				{ { true, true, true, false }, { false, false, true, false },
						{ false, false, false, false },
						{ false, false, false, false } } };
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;

	}
}
