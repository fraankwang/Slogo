/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;
import constants.Constants;

public class MainModel {
	
	private String myLanguage;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	private UserCommands myUserCommands;
	private CommandParser myParser;
	private History myHistory;
	private History myOutputs;

	public MainModel(String language) {
		myLanguage = language;
		myVariables = new Variables();
		myPlayground = new TurtlePlayground(Constants.PLAYGROUND_HEIGHT, Constants.PLAYGROUND_HEIGHT);

		myVariables = new Variables();
		myUserCommands = new UserCommands();

		myParser = new CommandParser(myLanguage, myPlayground, myVariables, myUserCommands);
		myHistory = new History();
		myOutputs = new History();

	}

	public void readCommand(String input) {
		System.out.println("command read: " + input);
		try {
			myOutputs.add(myParser.parseCommands(input).toString());
			myHistory.add(input);

		} catch (Exception e) {
			myOutputs.add(e.getMessage());
			// e.printStackTrace();
		}
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

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
	
	public void replaceVariableValue(String name, String newVal){
		myVariables.replaceVariableValue(name, newVal);
	}

}