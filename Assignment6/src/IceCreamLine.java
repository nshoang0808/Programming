import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class IceCreamLine extends JComponent {
	private Queue<IceCreamCone> orderLine;
	private Queue<IceCreamConeView> orderLineView;
	
	public IceCreamLine() {
		orderLine = new QueueLL<IceCreamCone>();
		orderLineView = new QueueLL<IceCreamConeView>();
		//Set the layout to be the box
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layout);
	}
	
	/**
	 * @return true if the line still contains orders.
	 */
	public boolean hasOrder() {
		return !orderLine.isEmpty();
	}
	
	/**
	 * Add an order of ice cream to the queue.
	 * @param cone
	 */
	public void addOrder(IceCreamCone cone) {
		//Set the size of ice cream cone order appear on application 
		cone.setScale(0.5d);
		orderLine.enqueue(cone);
		//Create the view and add to the the BoxLayout
		IceCreamConeView coneView = new IceCreamConeView(cone);
		this.add(coneView);
		validate();
		orderLineView.enqueue(coneView);
	}
	
	/**
	 * Add a random order of ice cream to the queue
	 */
	public void addRandomOrder() {
		IceCreamCone newCone = IceCreamCone.createRandom();
		addOrder(newCone);
	}
	
	/**
	 * Serve (remove) the current order and get the next order in Queue
	 * @return
	 * 			current order (IceCreamCone) in the queue.
	 */
	public IceCreamCone getNextOrder() {
		if (hasOrder()) {
			IceCreamCone cone = orderLine.dequeue();
			this.remove(orderLineView.dequeue());
			validate();
			return cone;
		}
		return null;
	}
	
	/**
	 * Extra method for the timer game: reset the ice cream line back to no order
	 */
	public void reset() {
		while(hasOrder()) getNextOrder();
	}
	
	/**
	 * Extra method for the timer game: count the number of orders in line
	 */
	public int length() {
		Queue<IceCreamCone> tmpLine = new QueueLL<IceCreamCone>();
		int count = 0;
		while (!orderLine.isEmpty()) {
			tmpLine.enqueue(orderLine.dequeue());
			count++;
		}
		while (!tmpLine.isEmpty()) {
			orderLine.enqueue(tmpLine.dequeue());
		}
		return count;
	}
}
