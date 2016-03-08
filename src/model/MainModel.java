/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;
import constants.Constants;
import model.turtle.TurtlePlayground;

/**
 * The MainModel class represents the core-model of the project. It contains the
 * logic of the back-end of the project, and puts together all of the smaller
 * logical components of the logic behinds slogo. The MainModel class has
 * instances of a String myLanguage, a TurtlePlayground myPlayground, a
 * Variables object myVariables, a UserCommands object myUserCommands, a
 * CommandParser object myParser, and two History objects myHistory and
 * myOutputs
 * 
 */
public class MainModel {

	private String myLanguage;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	private UserCommands myUserCommands;
	private CommandParser myParser;
	private History myHistory;
	private History myOutputs;
	private Palette myPalette;
	private Configuration myConfiguration;

	public MainModel(String language) {
		myLanguage = language;
		myVariables = new Variables();
		myPlayground = new TurtlePlayground(Constants.PLAYGROUND_WIDTH, Constants.PLAYGROUND_HEIGHT);

		myVariables = new Variables();
		myUserCommands = new UserCommands();

		myParser = new CommandParser(myLanguage, myPlayground, myVariables, myUserCommands, myPalette, myConfiguration);
		myHistory = new History();
		myOutputs = new History();

	}

	/**
	 * The readCommand() method reads in user inputs stored as strings and
	 * processes them to move the turtle as the user intends. The readCommand()
	 * method adds the user input to myHistory The readCommand() method adds the
	 * parsed' commands output in string format to myOutputs.
	 */
	public void readCommand(String input) {
		System.out.println("command read: " + input);
		try {
			myOutputs.add(myParser.parseCommands(input).toString());
			myHistory.add(input);

		} catch (Exception e) {
			myOutputs.add(e.getMessage());

			e.printStackTrace();
		}
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public void setLanguage(String language) {
		myLanguage = language;
		myParser = new CommandParser(myLanguage, myPlayground, myVariables, myUserCommands);
	}

	public CommandParser getMyParser() {
		return myParser;
	}

	public TurtlePlayground getMyPlayground() {
		return myPlayground;
	}

	public Collection<String> getMyOutputs() {
		return myOutputs.getHistory();
	}

	public Collection<String> getMyHistory() {
		return myHistory.getHistory();
	}

	public Map<String, Double> getMyVariables() {
		return myVariables.getVariableMap();
	}

	public Map<String, List<String>> getMyUserCommands() {
		return myUserCommands.getUserCommandMap();
	}

	public void replaceVariable(String oldVar, String newVar) {
		myVariables.replaceVariable(oldVar, newVar);
	}

	public void replaceVariableValue(String name, String newVal) {
		myVariables.replaceVariableValue(name, newVal);
	}

	public Configuration getConfiguration() {
		return myConfiguration;
	}

	public Palette getPalette() {
		return myPalette;
	}

}