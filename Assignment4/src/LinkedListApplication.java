/**
 * This method basically prints things out to see if they work.
 * 
 * @author Van
 *
 */
public class LinkedListApplication {
	public static void main(String[] args) {
		// Create a new list
		LinkedList<String> list = new LinkedList<String>();

		// A variable for the head of the list
		LinkedListNode<String> head;
		// 1st insertion
		list.insertFirst("a");
		// Print out the list
		System.out.println(list.toString());

		// 2nd insertion
		list.insertFirst("v");
		// Print out the list
		System.out.println(list.toString());

		// 3rd insertion
		list.insertFirst("a");
		// Print out the list
		System.out.println(list.toString());

		// 4th insertion
		list.insertFirst("l");
		// Print out the list
		System.out.println(list.toString());

		// 5th insertion
		list.insertFirst("o");
		// Print out the list
		System.out.println(list.toString());

		// 6th insertion
		list.insertFirst("i");
		// Print out the list
		System.out.println(list.toString());

		// Get the head and the node after head
		head = list.getFirstNode();
		// The node after the head node
		LinkedListNode<String> afterHead = head.getNext();

		// 1st insertion after the afterHead node
		list.insertAfter(afterHead, "j");
		// Print out the list
		System.out.println(list.toString());

		// 2nd insertion after the afterHead node
		list.insertAfter(afterHead, "e");
		// Print out the list
		System.out.println(list.toString());

		// 3rd insertion after the afterHead node
		list.insertAfter(afterHead, "v");
		// Print out the list
		System.out.println(list.toString());

		// Insertion after the head node
		list.insertAfter(head, "l");
		// Print out the list
		System.out.println(list.toString());

		// Deletion - this deletion method doesn't seem to work
		//]list.deleteNext(head.getNext().getNext().getNext().getNext());

		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		list.deleteLast();
		// Print out the list
		System.out.println(list.toString());
		System.out.println(list.isEmpty());
	}

}
