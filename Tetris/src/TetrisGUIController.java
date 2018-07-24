/**
 * A panel that is responsible for key events and keeping track of the game
 * It is the "Controller" in the Model-View-Controller
 * @author VanTrinh
 *
 */
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class TetrisGUIController extends JPanel implements KeyListener {

	// The speed of dropping the piece (miliseconds)
	private final int DEFAULT_DROP_RATE = 700;
	// The timer of the game
	private Timer gameTimer;
	// A variable for keeping track of the Tetris game (the model)
	private TetrisGame game;
	// A variable for keeping track of the Tetris view
	private TetrisBoardGUIView view;
	// A label for the number of lines cleared
	private JLabel numLines;
	// A label for the number of tetrises cleared
	private JLabel numTetrises;

	/**
	 * The constructor of Tetris GUI Controller
	 */
	public TetrisGUIController() {
		// Call the super class of JPanel and give it border layout
		super(new BorderLayout());
		createView();
		setupTimer();
	}

	/**
	 * This method sets up a Timer using ActionLisnter and actionPerfomed
	 */
	private void setupTimer() {
		// Add a new action listener
		ActionListener listener = new ActionListener() {
			// Implement actionPerformed method required by the listener
			public void actionPerformed(ActionEvent evt) {
				// Move the Tetris piece downs
				game.attemptMove(TetrisGame.DOWN);
				// Repaint the board, update label
				refreshDisplay();
			}
		};
		// Set up timer
		gameTimer = new Timer(DEFAULT_DROP_RATE, listener);
		// Set an initial delay
		gameTimer.setInitialDelay(1);
		// Start timer
		gameTimer.start();
	}

	/**
	 * This method creates the view of the controller with labels and panels
	 */
	private void createView() {
		// Initialize the game rules
		game = new TetrisGame();

		// Create a label for number of lines
		numLines = new JLabel("Lines cleared           " + game.getNumLines());
		// Create a new label for the number of tetrises
		numTetrises = new JLabel("Tetrises cleared       "
				+ game.getNumTetrises());

		// Create a new panel with grid layout to store the above labels
		JPanel northPanel = new JPanel(new GridLayout(2, 0));
		// Add this panel to the north of the current GUI Controller panel
		this.add(northPanel, BorderLayout.NORTH);
		// Add labels to the panel
		northPanel.add(numLines);
		northPanel.add(numTetrises);

		// Initialize the board GUI view
		view = new TetrisBoardGUIView(game.getTetrisBoard());
		// Add the GUI View (a JComponent) to the panel --> helps determine size
		// of frame
		this.add(view, BorderLayout.CENTER);

		// Make Key listener focus on the component
		setFocusable(true);
		// Add a key listener
		addKeyListener(this);
	}

	/**
	 * Method to repaint whenever there's a keyboard input and updates the
	 * labels
	 */
	public void refreshDisplay() {
		// Update label of number of lines cleared
		numLines.setText("Lines cleared           " + game.getNumLines());
		// Update label of number of tetrises cleared
		numTetrises.setText("Tetrises cleared       " + game.getNumTetrises());
		// Repaint the view
		repaint();
	}

	/**
	 * Do something when a key is pressed
	 * 
	 * @param e
	 *            the key that is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// "KeyCode" is the enum of the key that was pressed
		// Check the KeyEvent documentation for more
		int key = e.getKeyCode();

		// Execute different movements based on different keys pressed
		if (key == KeyEvent.VK_DOWN) {
			game.attemptMove(TetrisGame.DOWN);
		} else if (key == KeyEvent.VK_LEFT) {
			game.attemptMove(TetrisGame.LEFT);
		} else if (key == KeyEvent.VK_RIGHT) {
			game.attemptMove(TetrisGame.RIGHT);
		} else if (key == KeyEvent.VK_X) {
			game.attemptMove(TetrisGame.CW);
		} else if (key == KeyEvent.VK_Z) {
			game.attemptMove(TetrisGame.CCW);
		} else if (key == KeyEvent.VK_SPACE) {

		}
		// Repaint and update label
		refreshDisplay();
	}

	/**
	 * Move the circle when a key is released
	 * 
	 * @param e
	 *            the key that is released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Do something when a key is pressed and released
	 * 
	 * @param e
	 *            the key that is pressed and released
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

}
