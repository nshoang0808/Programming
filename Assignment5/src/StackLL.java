
public class StackLL<T> implements Stack<T> {
	
	// Global variable, including a LinkedList implemented in previous assignment
	private LinkedList<T> stack;

	/*
	 * Constructor of the stack
	 */
	public StackLL() {
		stack = new LinkedList<T>();
	}
	
	/*
	 * Add the element to the top of the stack.
	 */
	@Override
	public void push(T data) {
		stack.insertFirst(data);
	}

	/*
	 * @Return the element at the top of the stack,
	 * and remove it from the stack.
	 */
	@Override
	public T pop() {
		// tail is the top of the stack 
		T tail = null;
		if (!isEmpty()) {
			tail = stack.getFirst();
			stack.deleteFirst();
		}
		return tail;
	}

	/*
	 * @Return the element at the top of the stack
	 */
	@Override
	public T peek() {
		if (!isEmpty()) return stack.getFirst();
		return null;
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

}
