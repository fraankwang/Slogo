/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package controller;

import java.util.List;
import java.util.Queue;

import configuration.ConfigurationInfo;
import constants.Constants;
import javafx.animation.Timeline;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import model.MainModel;
import view.MainView;
import view.panelelements.PanelElement;
import view.panelelements.TurtleElement;

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
		myTransformer.transformTurtleGraphics(myModel.getMyPlayground());
		myTransformer.transformVariablesElement(myModel.getMyVariables());
		myTransformer.transformCommandsElement(myModel.getMyUserCommands());
		myTransformer.transformColorsElement(myModel.getPaletteMap());
		myTransformer.transformTurtleInfoElement(myModel.getMyPlayground().getTurtleList());

	}

	/**
	 * Grabs current turtle information and displays it in given TextArea
	 * 
	 * @param textArea
	 */
	public void displayTurtleInfo(TextArea textArea) {
		String language = "Language: " + myModel.getLanguage() + "\n";
		String ID = "Turtle ID: " + Integer.toString(myModel.getMyPlayground().getCurrentTurtleID()) + "\n";
		String orientation = "Orientation: " + myModel.getMyPlayground().getCurrentTurtle().getOrientation() % 360
				+ "\n";
		
		String penUp;
		if (myModel.getMyPlayground().getCurrentTurtle().getPenDown()) {
			penUp = "Pen is: down" + "\n";
		} else {
			penUp = "Pen is: up" + "\n";
		}

		String penColor = "Pen color: " + myModel.getMyPlayground().getCurrentPenColor() + "\n";
		
		Double xCoord = myView.getMyTurtleElement().getNode().getTranslateX();
		Double yCoord = myView.getMyTurtleElement().getNode().getTranslateY();
		if (yCoord != 0) {
			yCoord *= -1;
		}
		String x = "X Coordinate: " + Double.toString(xCoord) + "\n";
		String y = "Y Coordinate: " + Double.toString(yCoord);
		textArea.setText(language + ID + orientation + penUp + penColor + x + y);

	}
	
	/**
	 * Updates all model information given packaged configuration info
	 * @param configInfo
	 */
	public void updateConfiguration(ConfigurationInfo configInfo) {
		myModel.setLanguage(configInfo.getMyLanguage());
		myTransformer.setLanguage(configInfo.getMyLanguage());
		myModel.getMyPlayground().setCurrentPenColor(configInfo.getMyPenColor());
		myModel.getMyPlayground().setBackgroundColor(configInfo.getMyBackgroundColor());
		myModel.getMyPlayground().getCurrentTurtle().setPenSize(configInfo.getMyPenWidth());
		myModel.setMyVariables(configInfo.getMyVariables());
		myModel.setMyUserCommands(configInfo.getMyCommands());
		
	}

	/**
	 * Deletes stored previously run commands and moves turtle position back to
	 * start
	 */
	public void resetTurtlePosition() {
		myView.getMyTurtleGraphics().clearRect(0, 0, Constants.PLAYGROUND_WIDTH, Constants.PLAYGROUND_HEIGHT);
		myModel.getMyPlayground().getCurrentTurtle().clearTurtleCoordinates();
		myModel.getMyPlayground().setTurtleHome();
		TurtleElement turtleElement = (TurtleElement) myView.getMyTurtleElement();
		turtleElement.moveTurtleImage(0.0, 0.0);
		myTransformer.transformTurtleGraphics(myModel.getMyPlayground());

	}

	// =========================================================================
	// Modifiers, Getters, and Setters
	// =========================================================================

	public MainView getMyView() {
		return myView;
	}

	public MainModel getMainModel() {
		return myModel;
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
		myModel.getMyPlayground().setBackgroundColor(color);
	}

	public void setPenColor(Color color) {
		myModel.getMyPlayground().setCurrentPenColor(color);
	}

	public void setPenWidth(double value) {
		myModel.getMyPlayground().setCurrentPenSize(value);
	}

	public void setLanguage(String language) {
		myModel.setLanguage(language);
		myTransformer.setLanguage(language);
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

	public void setHelpMenu(boolean basic) {
		myView.showHelpScene(basic);
	}

	public void setPrimaryPane() {
		myView.showPrimaryScene();
	}


}

