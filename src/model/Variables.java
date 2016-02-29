/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

public class Variables {
	
	Map<String, Double> myVariables;
	
	public Variables(){
		myVariables=new HashMap<String, Double>();
	}
	
	public Collection<String> getVariables () {
		return myVariables.keySet();
	}
	
	public void addVariable (String name, double value) {
		myVariables.put(name, value);
	}
	
	public double getVariableValue (String name) {
		return myVariables.get(name);
	}
	
	
}
