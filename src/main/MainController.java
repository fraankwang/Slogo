/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import model.MainModel;

public class MainController {

	private MainView myView;
	private MainModel myModel;
	// private Timeline myTimeline;

	public MainController(MainView view) {
		myView = view;
		myModel = new MainModel();

	}

	/**
	 * Executes a command whenever user enters text into the input area. Step 1
	 * sends information to be parsed and updated in the model Step 2 gets the
	 * most recent model information and translates it back for the view
	 * 
	 * @param input
	 *            the command(s) the user types
	 */
	public void executeCommand(String input) {
		readCommand(input);
		refreshDisplay();

	}

	/**
	 * Part 1 of executeCommand. Calls corresponding method in myModel, which
	 * deals with parsing and implementing it.
	 * 
	 * @param input
	 */
	private void readCommand(String input) {
		myModel.readCommand(input);
	}

	/**
	 * Part 2 of executeCommand. Calls corresponding update methods to refresh
	 * changing UI elements in the view
	 */
	private void refreshDisplay() {
		// get myView's outputbox, historybox, turtlebox and call respective
		updateOutputBox();
		updateHistoryBox();
		updateTurtleBox();

	}

	/**
	 * Updates what is visible in the console to the user
	 */
	private void updateOutputBox() {
		// modify elements within outputbox so we don't have to deal with actual
		// UI wrapper
		// myView.getMyOutputBox().text or whatever
	}

	/**
	 * Updates what is visible in the history to the user
	 */
	private void updateHistoryBox() {
		// myView.getMyHistoryBox() and modify element within it so we don't
		// deal with actual UI wrapper
	}

	/**
	 * Updates where the turtle (or turtles) has drawn
	 */
	private void updateTurtleBox() {
		//
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================
	
	public void setTurtleColor(Color color) {
		// updates Model information, needs to refresh display

	}

	public void setBackgroundColor(Color color) {

	}

	public void setTurtleImage(String string) {
		// updates Model information, needs to refresh display

	}

	public void setPenColor(Color color) {
		// updates Model information, needs to refresh display

	}

	public void setLanguage(String value) {
		
	}

}
