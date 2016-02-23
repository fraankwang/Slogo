/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

public class History {
	private Queue<String> myHistory;
	
	public History(){
		myHistory=new PriorityQueue<String>();
		// @Srikar @Huijia, please follow a uniform coding checkstyle
		/**
		 * myHistory = new PriorityQueue<String>();
		 * if (condition == condition2) { <--- notice the spacing
		 * return myHistory.size() == 0 <--- again notice the spacing
		 * for (int row; row < getRows(); row++) { 
		 * etc etc. Keep spacing consistent and separate if statements brackets on separate lines 
		 */
	}
	
	/**
	 * TODO fill this out
	 * @param command
	 */
	public void add (String command) {
		myHistory.add(command);
	}
	
	public Collection<String> getHistory () {
		return myHistory;
		//should be a queue
	}
	
	public boolean isEmpty(){
		return myHistory.size()==0;
	}
}
