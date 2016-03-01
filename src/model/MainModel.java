/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

import constants.Constants;
import controller.MainController;

public class MainModel {
	private MainController myController;
	// we can eliminate a dependency with MainModel sending stuff to myController if we have getters 
	// in the MainModel class (i.e. getHistory) that calls respective Model elements to return their values

	// for example: MainController.sendStuff(MainModel), MainController.getStuff(MainModel). 
	// Avoids MainModel.sendStuff(MainController) 

	// I think the MainController should still do the translating
	private String myLanguage;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	private UserCommands myUserCommands;
	private CommandParser myParser;
	private History myHistory;
	private History myOutputs;
	
	public MainModel(String language) {
		myLanguage = language;
		myVariables=new Variables();
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
//			e.printStackTrace();
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

	public Map<String,Double> getMyVariables() {
		return myVariables.getVariableMap();
	}
	
	public Map<String,List<String>> getMyUserCommands() {
		return myUserCommands.getUserCommandMap();
	}

	public void modifyVariable(String oldVar, String newVar) {
		// TODO Auto-generated method stub
		myVariables.addVariable(newVar, myVariables.getVariableValue(oldVar));
		myVariables.deleteVariable(oldVar);
	}

}