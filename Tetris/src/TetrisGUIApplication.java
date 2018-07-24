/**
 * This class provides the application for the GUI version of Tetris.
 * @author VanTrinh
 *
 */
import javax.swing.JFrame;

public class TetrisGUIApplication {

	// The height of frame
	public static int FRAME_WIDTH = 400;
	// The width of frame
	public static int FRAME_HEIGHT = 750;

	/**
	 * Create a JFrame that holds the board of Tetris
	 * 
	 * @param args
	 *            the argument
	 **/
	public static void main(String[] args) {
		// A new GUI frame
		JFrame guiFrame;

		// if argument "tetris" is passed in
//		if (args.length > 0 && args[0].equals("tetris")) {
			// Create a new frame for Tetris game
			guiFrame = new JFrame("Tetris");

			// Set size of the frame
			guiFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

			// Add the label Tetris GUI Controller
			guiFrame.add(new TetrisGUIController());

			// Exit normally on closing the window
			guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Show the frame
			guiFrame.setVisible(true);
//		}
	}
}
