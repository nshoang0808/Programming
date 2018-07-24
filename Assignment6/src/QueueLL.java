
public class QueueLL<T> implements Queue<T> {
	// A linked list to contain element in queue.
	private LinkedList<T> queue;
	
	public QueueLL() {
		queue = new LinkedList<T>();
	}
	
	/**
	 * Return true if this queue is empty
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * @Return the element at the top of the queue
	 */
	@Override
	public T peek() {
		if (!queue.isEmpty()) return queue.getLast();
		return null;
	}

	/**
	 * @Return the element at the top of the queue,
	 * and remove it from the queue.
	 */
	@Override
	public T dequeue() {
		T elem = null;
		if (!queue.isEmpty()) {
			elem = queue.getLast();
			queue.deleteLast();
		}
		return elem;
	}

	/**
	 * Add the element to the top of the queue.
	 * @param data
	 * 				element to add to queue
	 */
	@Override
	public void enqueue(T data) {
		queue.insertFirst(data);
	}
	
	/** 
	 * Returns a String representation of the queue.
	 * @return queue as String
	 **/
	@Override
	public String toString() {
		return "";
	}

}
