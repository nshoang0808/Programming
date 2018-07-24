/**
 * This class tests the Linked List Node class
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	// Create a new list
	LinkedList<String> list = new LinkedList<String>();
	// The head of the list
	LinkedListNode<String> head;
	// The tail of the list
	LinkedListNode<String> tail;
	// The variable of the node after the head
	LinkedListNode<String> afterHead;

	@Before
	public void testLinkedList() {
		// 1st insertion
		list.insertFirst("a");
		System.out.println(list.toString());
		// 2nd insertion
		list.insertFirst("v");
		System.out.println(list.toString());
		// 3rd insertion
		list.insertFirst("a");
		System.out.println(list.toString());
		// 4th insertion
		list.insertFirst("l");
		System.out.println(list.toString());
		// 5th insertion
		list.insertFirst("o");
		System.out.println(list.toString());
		// 6th insertion
		list.insertFirst("i");
		System.out.println(list.toString());
		// Get the head and the node after head
		head = list.getFirstNode();
		afterHead = head.getNext();
		// Middle insertion 1
		list.insertAfter(afterHead, "j");
		System.out.println(list.toString());
		// Middle insertion 2
		list.insertAfter(afterHead, "e");
		System.out.println(list.toString());
		// Middle insertion 3
		list.insertAfter(afterHead, "v");
		System.out.println(list.toString());
		// Insertion after the head node
		list.insertAfter(head, "l");
		System.out.println(list.toString());
		// Deletion
		list.deleteNext(head.getNext().getNext().getNext().getNext());
		System.out.println(list.toString());
		System.out.println();
		tail = list.getLastNode();
	}

	@Test
	public void testGetFirst() {
		assertEquals("The data of the first node should be i", "i", // correct
																	// answer
				list.getFirst());
	}

	@Test
	public void testGetFirstNode() {
		assertEquals("The first node of list should be the head", head, // correct
				// answer
				list.getFirstNode());
	}

	@Test
	public void tesGetLast() {
		assertEquals("The data of the last node of the list is a", "a", // correct
																		// answer
				list.getLast());
	}

	@Test
	public void testGetLastNode() {
		assertEquals("The last node of the list is the tail", tail, // correct
																	// answer
				list.getLastNode());
	}

	@Test
	public void testInsertFirst() {
		assertNotNull("The first node is inserted and is not null", head);
	}

	@Test
	public void testInsertAfter() {
		assertNotNull("The node inserted after the first node is not null",
				afterHead);
	}

	@Test
	public void testInsertLast() {
		// Insert the last node
		list.insertLast("a");
		// A variable for last node
		LinkedListNode<String> lastNode = new LinkedListNode<String>();
		// Iterate through the whole list to find the last node
		for (LinkedListNode<String> node = head; node != null; node = node
				.getNext()) {
			// If node after this node is null
			if (node.getNext() == null) {
				// Node is the last node
				lastNode = node;
			}
		}
		assertEquals("Last node should be a", "a", lastNode.getData());
	}

	@Test
	public void testDeleteFirst() {
		// Create a new list
		LinkedList<String> theList = new LinkedList<String>();
		// Insert into the list
		theList.insertFirst("a");
		theList.insertFirst("b");
		// A variable for the first node
		LinkedListNode<String> firstNode = theList.getFirstNode();
		// Delete the first node
		theList.deleteFirst();
		assertEquals(
				"After deleting the first node a, the first node now is b",
				"b", firstNode.getData());
	}

	@Test
	public void testDeleteLast() {
		// Create a new list
		LinkedList<String> theList = new LinkedList<String>();
		// Insert into the list
		theList.insertFirst("b");
		theList.insertFirst("a");
		// A variable for the first node
		LinkedListNode<String> firstNode = theList.getFirstNode();
		// Variable for the second node
		LinkedListNode<String> secondNode = firstNode.getNext();
		// Delete the second node
		theList.deleteLast();
		assertNotNull("Last node should be null", secondNode);
	}

	@Test
	public void testSize() {
		assertEquals("The size of the node should be 9", 9, // correct answer
				list.size());
	}

	@Test
	public void testIsEmpty() {
		assertEquals("The list should not be empty", false, list.isEmpty());
	}
}
