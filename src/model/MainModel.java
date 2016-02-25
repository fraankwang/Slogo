/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import controller.MainController;

public class MainModel {
	private MainController myController;
	// we can eliminate a dependency with MainModel setnding stuff to myController if we have getters 
	// in the MainModel class (i.e. getHistory) that calls respective Model elements to return their values

	// for example: MainController.sendStuff(MainModel), MainController.getStuff(MainModel). 
	// Avoids MainModel.sendStuff(MainController) 

	// I think the MainController should still do the translating
	
	private CommandParser myParser;
	private History myHistory;
	private TurtlePlayground myPlayground;
	private Variables myVariables;

	public MainModel() {

	}

	public void readCommand(String input) {
		// wrapper method that does everything (extensive use of helper classes
		// and objects)
		// parse command
		// calculate/run the command
		// store command in history and update history
		// anything else I missed
		System.out.println("command read: " + input);
	}

	/** NOTE to Huijia and Srikar:
	 * whatever the final data structure is like for all the coordinates to plot,
	 * you need to have some way to tell if the pen is up or down for each of them, maybe like a
	 * list of (x, y, penUp) tuples sort of data structure. A double[] is not going to be good enough
	 * 
	 */
	
	// =========================================================================
	// Getters and Setters
	// =========================================================================
	
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
