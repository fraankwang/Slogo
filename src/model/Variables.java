/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

/**
 * The Variables class is a custom object which holds user-defined variables The
 * UserCommands class has an instance of a Map<String, Double> myVariables
 * myVariables maps string variable names to the value of the variable.
 * 
 */
public class Variables {

	Map<String, Double> myVariables;

	public Variables() {
		myVariables = new HashMap<String, Double>();
	}

	public Variables(Map<String, Double> vars) {
		myVariables = new HashMap<String, Double>(vars);
	}
	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public Map<String, Double> getVariableMap() {
		return myVariables;
	}

	public Collection<String> getVariables() {
		return myVariables.keySet();
	}

	public double getVariableValue(String name) {
		return myVariables.get(name);
	}

	/**
	 * The addVariables() method adds a variable to the myVariables map
	 * The addVariables() method puts a string and a double into the map.
	 */
	public void addVariable(String name, double value) {
		myVariables.put(name, value);
	}

	/**
	 * The replaceVariable method replaces an old variable with a new variable name
	 * The method puts a new string value pair, utilizing the new variable name as the string and still using the old variable value as the value.
	 * The method then removes the old variable from the map.
	 */
	public void replaceVariable(String oldVar, String newVar) {
		myVariables.put(newVar, myVariables.get(oldVar));
		myVariables.remove(oldVar);
	}

	/**
	 * The replaceVariableValue() method updates a variable value in the myVariable map.
	 * 
	 */
	public void replaceVariableValue(String name, String newVal) {
		myVariables.remove(name);
		myVariables.put(name, Double.parseDouble(newVal));
	}

}
