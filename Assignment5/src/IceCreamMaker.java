import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class IceCreamMaker extends JPanel implements ActionListener {
	private IceCreamCone cone;
	private IceCreamConeView coneView;
	private JButton[] flavorBtns;
	
	public IceCreamMaker() {
		super(new BorderLayout());
		cone = new IceCreamCone();
		addMenu();
	}
	
	/**
	 * Method to refresh the display when adding or removing scoops
	 */
	public void refreshDisplay() {
		this.repaint();
	}
	
	/**
	 * Method add all menu button and the iceCreamConeView to the layout
	 */
	private void addMenu() {
		// Create flavorMenu and trashButton.
		JPanel flavorMenu = createFlavorMenu();
		JButton trashButton = createTrashButton();
		
		//Create container to contain both flavor menu and trash button
		JPanel container = new JPanel(new GridLayout(2, 1));
		container.add(flavorMenu);
		container.add(trashButton);
		this.add(container, BorderLayout.NORTH);
		
		// Initialize the view to draw
		coneView = new IceCreamConeView(cone);
		this.add(coneView, BorderLayout.CENTER);
	}
	
	/**
	 * Returns a menu of all flavors to add to the ice cream
	 */
	private JPanel createFlavorMenu() {
		flavorBtns = new JButton[cone.FLAVORS.length];
		for (int id=0; id<flavorBtns.length; id++) {
			flavorBtns[id] = new JButton(cone.FLAVORS[id]);
			// Set background color of the button
			flavorBtns[id].setBackground(coneView.PAINT_COLORS[id]);
			// Set color of the button's title
			flavorBtns[id].setForeground(Color.black);
			// Set the action command to the name of the flavor need to draw
			flavorBtns[id].setActionCommand(cone.FLAVORS[id]);
			flavorBtns[id].addActionListener(this);
		}
		JPanel menu = new JPanel(new GridLayout(1, flavorBtns.length));
		for(int id=0; id<flavorBtns.length; id++) {
			menu.add(flavorBtns[id]);
		}
		
		return menu;
	}
	
	/**
	 * Returns trashButton to remove the top scoop
	 */
	private JButton createTrashButton() {
		JButton trashButton = new JButton("Trash (or eat) the top scoop!");
		trashButton.setBackground(new Color(176, 224, 230));
		trashButton.setForeground(Color.black);
		trashButton.setActionCommand("trash");
		//Set text to be at the center of the button
		trashButton.setHorizontalAlignment(SwingConstants.CENTER);
		trashButton.addActionListener(this);
		return trashButton;
	}
	
	/**
	 * Method to declare suitable actions according to pressed button
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String flavorCommanded = e.getActionCommand();
		
		// remove the top scoop if the command is to trash
		if (flavorCommanded.equals("trash")) {
			cone.popTopScoop();
		} else {
			// Find the flavor need to add by checking the command string = flavor's name
			for (int id=0; id<flavorBtns.length; id++) {
				if (flavorCommanded.equals(flavorBtns[id].getText())) {
					cone.addScoop(flavorCommanded);
				}
			}
		}
		refreshDisplay();
	}
}