/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package controller;

import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import model.MainModel;
import view.MainView;

public class MainController {

	private MainView myView;
	private MainModel myModel;
	private ModelTransformer myTransformer;
	// private Timeline myTimeline;

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
	 * Part 2 of executeCommand. Calls corresponding update methods to refresh
	 * changing UI elements in the view
	 */
	private void refreshDisplay() {
		myTransformer.transformOutputBox();
		myTransformer.transformHistoryBox();
		myTransformer.transformTurtleBox();
		myTransformer.transformVariablesBox();
		myTransformer.transformCommandsBox();

	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================
	
	public MainView getMyView() {
		return myView;
	}

	public void setTurtleImage(String image) {
		myTransformer.setTurtleImage(image);		
	}

	public void setBackgroundColor(Color color) {
		getMyView().setTurtleBackgroundColor(color);
	}

	public void setPenColor(Color color) {
		myTransformer.setPenColor(color);
	}

	public void setLanguage(String language) {
		myTransformer.setLanguage(language);
	}

}
