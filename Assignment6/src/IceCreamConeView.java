import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class IceCreamConeView extends JComponent {
	
	// List of colors for each of flavors
	public static final Color[] PAINT_COLORS = {Color.pink, new Color(224, 255, 255), new Color(222, 184, 135), new Color(220, 220, 220)};
	
	// Parameters for the cone and all scoops.
	public static final int CONE_WIDTH = 40;
	public static final int CONE_HEIGHT = 75;
	public static final int SCOOP_DIAMETER = 60;
	public static final int SCOOP_OVERLAP = 10;
	
	private IceCreamCone cone;
	
	/**
	 * 
	 * @param cone
	 * 				View of this ice-cream cone
	 */
	public IceCreamConeView(IceCreamCone cone) {
		this.cone = cone;
	}
	
	/**
	 * Method to paint the cone and all scoop flavors added
	 * @param g
	 *        		the graphics
	 */
	public void paint(Graphics g) {
		paintCone(g);
		paintScoops(g);
	}
	
	private int rescale(int x) {
		return (int) Math.round(x*cone.getScale());
	}
	
	/**
	 * Method to paint cone (a triangle)
	 * @param g
	 * 				the graphics
	 */
	public void paintCone(Graphics g) {
		// Draw the triangle cone with coordinates as follow
		int[] xPoints = {this.getWidth()/2, this.getWidth()/2+rescale(CONE_WIDTH/2), this.getWidth()/2-rescale(CONE_WIDTH/2)};
		int[] yPoints = {this.getHeight(), this.getHeight()-rescale(CONE_HEIGHT), this.getHeight()-rescale(CONE_HEIGHT)};
		g.setColor(new Color(238, 213, 183));
		g.drawPolygon(xPoints, yPoints, 3);
		g.fillPolygon(xPoints, yPoints, 3);
	}

	/**
	 * Method to paint one scoop
	 * @param g
	 * 				the graphics
	 * @param flavorIndex
	 * 				the index of flavor
	 * @param x
	 * 				x-coordinate of top-left rectangle box wrapping around the scoop (circle)
	 * @param y
	 * 				y-coordinate of top-left rectangle box wrapping around the scoop (circle)
	 */
	public void paintScoop(Graphics g, int flavorIndex, int x, int y) {
		// Set scoop color based on flavorIndex
		g.setColor(PAINT_COLORS[flavorIndex]);
		// Draw the circle representing the scoop with given color (flavor)
		g.drawOval(x, y, rescale(SCOOP_DIAMETER), rescale(SCOOP_DIAMETER));
		g.fillOval(x, y, rescale(SCOOP_DIAMETER), rescale(SCOOP_DIAMETER));
	}
	
	/**
	 * Method to paint all the scoops
	 * @param g
	 * 				the graphics
	 */
	private void paintScoops(Graphics g) {
		// Reverse the scoop stack to draw from bottom to top of the ice cream in correct order
		Stack<Integer> scoopStack = cone.getScoopFlavorStack();
		Stack<Integer> reverseScoopStack = new StackLL<Integer>();

		while (!scoopStack.isEmpty()) {
			reverseScoopStack.push(scoopStack.pop());
		}
		//Set the current coordinate of the first scoop, then draw each scoop with updated coordinate later.
		int x = this.getWidth()/2-rescale(SCOOP_DIAMETER/2), y = this.getHeight()-rescale(CONE_HEIGHT-SCOOP_OVERLAP+SCOOP_DIAMETER);
		
		//Draw all the scoop flavors in given orders.
		while (!reverseScoopStack.isEmpty()) {
			int flavorIndex = reverseScoopStack.pop();
			paintScoop(g, flavorIndex, x, y);
			y = y-rescale(SCOOP_DIAMETER-SCOOP_OVERLAP);
			scoopStack.push(flavorIndex);
		}
	}
}
