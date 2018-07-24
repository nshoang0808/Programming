import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IceCreamShop extends JPanel implements ActionListener {
	public final int CORRECT_MATCH_SCORE = 10;
	public final int INCORRECT_MATCH_SCORE = 5;
	public final String INSTRUCTIONS = "<html>Make ice cream cones to match the next order (at the top).<br>Every correct order served earns you "
	+ String.valueOf(CORRECT_MATCH_SCORE)+ " points.<br>Make the wrong cone and you'll lose " + String.valueOf(INCORRECT_MATCH_SCORE) + " points.</html>";
	
	public IceCreamLine line;
	public IceCreamMaker maker;
	private int score;
	private JLabel scoreLabel;
	
	public IceCreamShop() {
		super(new BorderLayout());
		maker = new IceCreamMaker();
		line = new IceCreamLine();
		addOrderArea();
		addControls();
	}
	
	/**
	 * Method to refresh the display when adding or removing scoops
	 */
	public void refreshDisplay() {
		this.repaint();
	}
	
	/**
	 * Add new order and ice cream line area to the application
	 */
	private void addOrderArea() {
		JPanel container = new JPanel(new GridLayout(3, 1));
		JLabel instruct = new JLabel(INSTRUCTIONS);
		container.add(instruct);
		
		scoreLabel = new JLabel("Score: "+String.valueOf(score));
		container.add(scoreLabel);
		
		JButton serveNextButton = createServeNext();
		container.add(serveNextButton);
		
		this.add(container, BorderLayout.NORTH);
	}
	
	/**
	 * Method to add the ice cream maker and the ice cream line to the application
	 */
	private void addControls() {
		this.add(maker, BorderLayout.CENTER);						//ice cream maker on the center-right side
		JComponent newOrderComponent = createNewOrderComponent();
		this.add(newOrderComponent, BorderLayout.WEST);				//Line order on the center-left side
	}
	
	/**
	 * create the new order component.
	 * @return
	 * 			a component that includes an ice cream line and an add order button
	 */
	private JComponent createNewOrderComponent() {		
		JComponent container = new JPanel(new BorderLayout());
		JButton newOrderBtn = createNewOrderButton();
		container.add(newOrderBtn, BorderLayout.SOUTH);
		container.add(line, BorderLayout.CENTER);
		return container;
	}
	
	/**
	 * create "add a random order" button
	 * @return
	 * 			a button add new order
	 */
	private JButton createNewOrderButton() {
		JButton newOrderBtn = new JButton("Add a random order");
		newOrderBtn.setActionCommand("create");
		//Set text to be at the center of the button
		newOrderBtn.setHorizontalAlignment(SwingConstants.CENTER);
		newOrderBtn.addActionListener(this);
		return newOrderBtn;
	}
	
	/**
	 * create "serve the next order" button
	 * @return
	 * 			a button to serve the current ice cream
	 */
	private JButton createServeNext() {
		JButton serveNextButton = new JButton("Serve the next order");
		serveNextButton.setActionCommand("serve");
		//Set text to be at the center of the button
		serveNextButton.setHorizontalAlignment(SwingConstants.CENTER);
		serveNextButton.addActionListener(this);
		return serveNextButton;
	}
	
	/**
	 * Method to serve the current ice cream to the current order
	 */
	public void serve() {
		// match the current cone to the current ordered cone
		IceCreamCone currentCone = maker.getCone();
		IceCreamCone orderedCone = line.getNextOrder();
		if (orderedCone == null) return;
		// update score accordingly
		if (matches(currentCone, orderedCone)) score = score + CORRECT_MATCH_SCORE;
		else score = score - INCORRECT_MATCH_SCORE;
		// then reset the ice cream maker
		maker.reset();
		updateScore();
	}
	
	/**
	 * Method to update the current score
	 */
	private void updateScore() {
		scoreLabel.setText("Score: "+String.valueOf(score));
		refreshDisplay();
	}
	
	/**
	 * @param c1
	 * 				first cone
	 * @param c2
	 * 				second cone
	 * @return
	 * 				true if 2 cones are similar, scoops by scoops in the same order
	 */
	public boolean matches(IceCreamCone c1, IceCreamCone c2) {
		//default is that 2 cones are similar until a pair of different scoop is found
		boolean isMatch = true;
		// 2 temporary ice cream cone to contains all scoop dropped during comparison
		IceCreamCone tmp1 = new IceCreamCone();
		IceCreamCone tmp2 = new IceCreamCone();
		
		//while either stack still has scoop left
		while (c1.hasScoop() || c2.hasScoop()) {
			String topScoop1= "", topScoop2 = "";
			
			if (c1.hasScoop()) {
				topScoop1 = c1.popTopScoop();
				tmp1.addScoop(topScoop1);
			}
			if (c2.hasScoop()) {
				topScoop2 = c2.popTopScoop();
				tmp2.addScoop(topScoop2);
			}
			
			//At least one different scoop found, it is not a match, so end the while loop check
			if (!topScoop1.equals(topScoop2)) {			
				isMatch = false;
				break;
			}
		}
		//return all the scoops have been popped out
		while (tmp1.hasScoop()) {
			String topScoop1 = tmp1.popTopScoop();
			c1.addScoop(topScoop1);
		}
		
		while (tmp2.hasScoop()) {
			String topScoop2 = tmp2.popTopScoop();
			c2.addScoop(topScoop2);
		}
		return isMatch;
	}

	/**
	 * Method to declare suitable actions according to clicked button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("serve")) {
			serve();
		} else {
			line.addRandomOrder();
		}
		refreshDisplay();
	}
}
