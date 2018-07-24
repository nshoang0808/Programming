import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IceCreamLineManager extends JPanel implements ActionListener {
	private IceCreamLine line;
	
	public IceCreamLineManager() {
		super(new BorderLayout());
		line = new IceCreamLine();
		addControls();
	}
	
	/**
	 * Method to refresh the display when adding or removing scoops
	 */
	public void refreshDisplay() {
		this.repaint();
	}
	
	/**
	 * Add all control components of the line into the application. 
	 */
	private void addControls() {
		JPanel container = new JPanel(new GridLayout(2, 1));
		JButton newOrderBtn = createNewOrderButton();
		JButton removeOrderBtn = createRemoveOrderButton();
		container.add(newOrderBtn);
		container.add(removeOrderBtn);
		this.add(container, BorderLayout.NORTH);
		this.add(line, BorderLayout.CENTER);
	}
	
	/**
	 * Create a button to add new order
	 * @return
	 * 			a create button
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
	 * Create a button to remove (serve) current order
	 * @return
	 * 			a remove button
	 */
	private JButton createRemoveOrderButton() {
		JButton removeOrderBtn = new JButton("Serve the next order");
		removeOrderBtn.setActionCommand("remove");
		//Set text to be at the center of the button
		removeOrderBtn.setHorizontalAlignment(SwingConstants.CENTER);
		removeOrderBtn.addActionListener(this);
		return removeOrderBtn;
	}

	/**
	 * Method to declare action to create or remove based on clicked button
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("create")) {
			line.addRandomOrder();
		} else {
			line.getNextOrder();
		}
		refreshDisplay();
	}
}
