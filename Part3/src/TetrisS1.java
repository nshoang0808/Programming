/**
 * This is the S tetris
 * 
 * @author VanTrinh
 *
 */
public class TetrisS1 extends TetrisPiece {

	public TetrisS1() {
		super();
		// Constructing S piece
		boolean[][][] theFilledSquares = {
				{ { false, true, true, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, false, false, false }, { true, true, false, false },
						{ false, true, false, false },
						{ false, false, false, false } },

				{ { false, true, true, false }, { true, true, false, false },
						{ false, false, false, false },
						{ false, false, false, false } },

				{ { true, false, false, false }, { true, true, false, false },
						{ false, true, false, false },
						{ false, false, false, false } } };
		// Set this to be equal to the parent class variable
		filledSquares = theFilledSquares;

	}
}
