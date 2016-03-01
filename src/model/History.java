/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

public class History {
	private Queue<String> myHistory;
	
	public History(){
		myHistory=new LinkedList<String>();
	}

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
