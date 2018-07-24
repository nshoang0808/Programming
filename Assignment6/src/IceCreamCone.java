
public class IceCreamCone {
	//	A constant list of ice cream flavors
	public static final String[] FLAVORS = {"strawberry", "green tea", "burnt caramel", "raspberry"};
	
	public static final int RANDOM_MAX_SCOOPS = 4; 
	//	A stack contains the current order of scoops in the cone
	private Stack<Integer> scoopFlavorStack;
	private double scale;
	
	/**
	 * Constructor instantiate scoops stack and the scale of the cone appeared on application
	 */
	public IceCreamCone() {
		scoopFlavorStack = new StackLL<Integer>();
		scale = 1.0d;
	}
	
	/**
	 * This method returns the current scoop flavor stack. 
	 */
	public Stack<Integer> getScoopFlavorStack() {
		return scoopFlavorStack;
	}
	
	/**
	 * This method returns the id of that flavor in FLAVORS list (to push into the stack by Id).
	 * @param flavor
	 * 				pass the chosen flavor. 
	 */
	private int findFlavorIdx(String flavor) {
		for(int id = 0; id<FLAVORS.length; id++) {
			if (FLAVORS[id].equals(flavor)) return id;
		}
		return -1;
	}
	
	/**
	 * This method add a scoop of chosen flavor to the stack
	 * @param flavor
	 * 				pass the chosen flavor. 
	 */
	public void addScoop(String flavor) {
		scoopFlavorStack.push(findFlavorIdx(flavor));
	}
	
	/**
	 * This method add a scoop of random flavor to the stack
	 */
	public void addScoop() {
		scoopFlavorStack.push((int)(Math.random()*4));
	}
	
	/**
	 * This method returns the top scoop flavors, and removes it.
	 */
	public String popTopScoop() {
		if (!scoopFlavorStack.isEmpty()) return FLAVORS[scoopFlavorStack.pop()];
		return "";
	}
	
	/**
	 * Set the scale of the ice cream cone by a double number
	 * @param s
	 */
	public void setScale(double s) {
		scale = s;
	}
	
	public double getScale() {
		return scale;
	}
		
	public boolean hasScoop() {
		return !scoopFlavorStack.isEmpty();
	}
	
	public static IceCreamCone createRandom() {
		IceCreamCone newCone = new IceCreamCone();
		int numScoop = (int)(Math.random()*(IceCreamCone.RANDOM_MAX_SCOOPS-1)+1);
		for (int scoopId=1; scoopId<=numScoop; scoopId++) {
			newCone.addScoop();
		}
		return newCone;
	}
}
