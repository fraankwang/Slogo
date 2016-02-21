/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import main.MainController;

public class MainModel {
	private MainController myController;
	private CommandParser myParser;
	private History myHistory;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	
	public void readCommand (String input) {
		// wrapper method that does everything (extensive use of helper classes and objects)
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
