/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;
import constants.Constants;
import javafx.scene.paint.Color;
import model.turtle.TurtlePlayground;

/**
 * The MainModel class represents the core-model of the project. It contains the
 * logic of the back-end of the project, and puts together all of the smaller
 * logical components of the logic behind slogo. The MainModel class has
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

	private Palette myPalette;

	private CommandParser myParser;
	private History myHistory;
	private History myOutputs;

	public MainModel(String language) {
		myLanguage = language;
		myVariables = new Variables();
		myPlayground = new TurtlePlayground(Constants.LEFT_COLUMN_WIDTH, Constants.PLAYGROUND_HEIGHT);

		myVariables = new Variables();
		myUserCommands = new UserCommands();

		myPalette = new Palette();

		myParser = new CommandParser(myLanguage, myPlayground, myVariables, myUserCommands, myPalette);
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
		System.out.println(myVariables.getVariableMap());
		System.out.println(myParser.getVariableList().getVariableMap());
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
		updateParser();
	}

	private void updateParser() {
		myParser = new CommandParser(myLanguage, myPlayground, myVariables, myUserCommands, myPalette);
	}

	public String getLanguage() {
		return myLanguage;
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

	public Variables getMyVariables() {
		return myVariables;
	}

	public void setMyVariables(Variables variables) {
		myVariables = variables;
		updateParser();
	}

	public UserCommands getMyUserCommands() {
		return myUserCommands;
	}

	public void setMyUserCommands(UserCommands commands) {
		myUserCommands = commands;
		updateParser();
	}

	public void replaceVariable(String oldVar, String newVar) {
		myVariables.replaceVariable(oldVar, newVar);
		updateParser();
	}

	public void replaceVariableValue(String name, String newVal) {
		myVariables.replaceVariableValue(name, newVal);
		updateParser();
	}

	public Palette getPalette() {
		return myPalette;
	}

	public Map<Integer, Color> getPaletteMap() {
		return myPalette.getPaletteColorMap();
	}

	public void setMyPalette(Palette palette) {
		myPalette = palette;
		updateParser();
	}

}