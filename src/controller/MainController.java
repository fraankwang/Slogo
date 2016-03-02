/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package controller;

import java.util.List;
import java.util.Queue;

import constants.Constants;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import model.MainModel;
import view.MainView;
import view.panelelements.PanelElement;

public class MainController {

	private MainView myView;
	private MainModel myModel;
	private ModelTransformer myTransformer;
	private Timeline myTimeline;

	public MainController(MainView view) {
		myView = view;
		myTransformer = new ModelTransformer(this);
		myModel = new MainModel(myTransformer.getLanguage());
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
	 * Part 2 of executeCommand. Refreshes every UI elements in the view
	 */
	public void refreshDisplay() {
		myTransformer.transformOutputElement((Queue<String>) myModel.getMyOutputs());
		myTransformer.transformHistoryElement((Queue<String>) myModel.getMyHistory());
		myTransformer.transformTurtleGraphics(myModel.getMyPlayground().getTurtle());
		myTransformer.transformVariablesElement(myModel.getMyVariables());
		myTransformer.transformCommandsElement(myModel.getMyUserCommands());

	}

	/**
	 * Erases canvas but maintains Turtle position TODO: implement this for
	 * extension
	 */
	public void clearTurtlePlayground() {
		myView.getMyTurtleGraphics().clearRect(0, 0, Constants.PLAYGROUND_WIDTH, Constants.PLAYGROUND_HEIGHT);
		myModel.getMyPlayground().getTurtle().clearTurtleCoordinates();
		myModel.getMyPlayground().setTurtleHome();

	}

	/**
	 * Deletes stored previously run commands and moves turtle position back to
	 * start
	 */
	public void resetTurtlePosition() {
		myView.getMyTurtleGraphics().clearRect(0, 0, Constants.PLAYGROUND_WIDTH, Constants.PLAYGROUND_HEIGHT);
		myModel.getMyPlayground().getTurtle().clearTurtleCoordinates();
		myModel.getMyPlayground().setTurtleHome();
		myView.getMyTurtleElement().moveTurtleImage(0.0, 0.0);
		myTransformer.transformTurtleGraphics(myModel.getMyPlayground().getTurtle());

	}

	// =========================================================================
	// Modifiers, Getters, and Setters
	// =========================================================================

	public MainView getMyView() {
		return myView;
	}

	public List<PanelElement> getViewableElements() {
		return myView.getViewableElements();
	}

	public void setTurtleImage(String image) {
		myView.setTurtleImage(image);
	}

	public void setTurtleElement(PanelElement turtleElement) {
		myTransformer.setTurtleElement(turtleElement);
	}

	public void setBackgroundColor(Color color) {
		myView.setTurtleBackgroundColor(color);
	}

	public void setPenColor(Color color) {
		myTransformer.setPenColor(color);
	}

	public void setLanguage(String language) {
		myModel.setLanguage(language);
	}

	public void replaceVariable(String oldVar, String newVar) {
		myModel.replaceVariable(oldVar, newVar);
	}

	public void replaceVariableValue(String name, String newVal) {
		myModel.replaceVariableValue(name, newVal);
	}

	public void setAnimationSpeed(double speed) {
		if (myTimeline != null) {
			myTimeline.setRate(speed);
		}
	}

	public void setHelpMenu() {
		myView.showHelpScene();
	}

	public void setPrimaryPane() {
		myView.showPrimaryScene();
	}

}
