/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import main.MainController;

public class MainModel {
	private MainController myController; 
	// we can eliminate a dependency if we have getters in the 
	// MainModel class (i.e. getHistory) that calls respective Model elements to return their values
	// I think the MainModel should also do the translating then
	private CommandParser myParser;
	private History myHistory;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	
	public MainModel(){
		
	}
	
	public void readCommand (String input) {
		// wrapper method that does everything (extensive use of helper classes and objects)
		// parse command
		// calculate/run the command
		// store command in history and update history
		// anything else I missed
		System.out.println("command read: " + input);
	}
	
	public CommandParser getMyParser() {
		return myParser;
	}
	public History getMyHistory() {
		return myHistory;
	}
	public TurtlePlayground getMyPlayground() {
		return myPlayground;
	}
	public Variables getMyVariables() {
		return myVariables;
	}
	
	
}
