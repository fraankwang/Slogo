/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

public class History {
	private Queue<String> myHistory;

	public History() {
		myHistory = new PriorityQueue<String>();
	}

	/**
	 * TODO fill this out
	 * 
	 * @param command
	 */
	public void add(String command) {
		myHistory.add(command);
	}

	public Queue<String> getHistory() {
		return myHistory;
	}

	public boolean isEmpty() {
		return myHistory.size() == 0;
	}
}
