import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimedIceCreamShop extends IceCreamShop {
	public final int GAME_TIME = 60; //60 seconds
	public final int TIME_FOR_NEW_ORDER = 3;
	public final int LINE_MAX_LENGTH = 4;
	
	private JLabel timeRemainingLabel;
	int timeRemaining;
	private Timer gameTimer, timeLeft;
	
	/**
	 * Constructor initiate the game, with time remaining set to max.
	 */
	public TimedIceCreamShop() {
		super();
		timeRemaining = GAME_TIME;		
		JComponent newOrderComponent = createNewOrderComponent();
		this.add(newOrderComponent, BorderLayout.WEST);				//Line order on the center-left side
	}
	
	/**
	 * Update the line order into a game that has timer
	 * @return
	 * 			Left component displaying the line order
	 */
	public JComponent createNewOrderComponent() {
		//Create time remaining label
		timeRemainingLabel = new JLabel("Time left: "+String.valueOf(timeRemaining)+"s");
		
		//Create start button
		JButton start = new JButton("Start!");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					createTimer();
			}
		});
		
		//Create reset button
		JButton reset = new JButton("RESET");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeRemaining = GAME_TIME;
				gameTimer.cancel();
				timeLeft.cancel();
				line.reset();
				maker.reset();
				updateTimeLabel();
				refreshDisplay();
			}
		});
		
		//A panel to contain both start button and timer
		JPanel gameTask = new JPanel(new GridLayout(2, 2));
		gameTask.add(start);
		gameTask.add(timeRemainingLabel);
		gameTask.add(reset);
		
		JComponent container = new JPanel(new BorderLayout());
		JButton newOrderBtn = createNewOrderButton();
		container.add(newOrderBtn, BorderLayout.SOUTH);
		container.add(line, BorderLayout.CENTER);
		container.add(gameTask, BorderLayout.NORTH);
		return container;
	}
	
	private JButton createNewOrderButton() {
		JButton newOrderBtn = new JButton("Add a random order");
		newOrderBtn.setActionCommand("create");
		//Set text to be at the center of the button
		newOrderBtn.setHorizontalAlignment(SwingConstants.CENTER);
		newOrderBtn.addActionListener(this);
		return newOrderBtn;
	}
	
	/**
	 * Method to start the timer.
	 */
	private void createTimer() {
		gameTimer = new Timer();
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				line.addRandomOrder();
				if (timeRemaining == 0 || line.length() > LINE_MAX_LENGTH) cancel();
			}
		}, 100, 3000);
		
		timeLeft = new Timer();
		timeLeft.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				updateTime();
				if (timeRemaining == 0 || line.length() > LINE_MAX_LENGTH) cancel();
			}
		}, 100, 1000);
	}
	
	/**
	 * Update the current time in game, including update the label
	 */
	public void updateTime() {
		timeRemaining--;
		updateTimeLabel();
	}
	
	/**
	 * Update time label panel
	 */
	private void updateTimeLabel() {
		timeRemainingLabel.setText("Time left: "+String.valueOf(timeRemaining)+"s");
		refreshDisplay();
	}
}
