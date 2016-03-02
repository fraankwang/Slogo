/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

/**
 * The History class is a custom class which contains a list of commands which
 * the user has executed The History class contains an instance of a Queue
 * <Strings> called myHistory which contains this list of commands in string
 * form
 */
public class History {
	private Queue<String> myHistory;

	public History() {

		myHistory = new LinkedList<String>();

	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public Queue<String> getHistory() {
		return myHistory;
	}

	/**
	 * The add() method adds a command to the myHistory queue.
	 *
	 */
	public void add(String command) {
		myHistory.add(command);
	}

	/**
	 * The isEmpty() method checks whether the myHistory queue is empty.
	 *
	 */
	public boolean isEmpty() {
		return myHistory.size() == 0;
	}
}
