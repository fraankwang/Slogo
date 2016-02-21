/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package src.model;

import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

public class History {
	private Queue<String> myHistory;
	
	public History(){
		myHistory=new PriorityQueue<String>();
	}
	
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
