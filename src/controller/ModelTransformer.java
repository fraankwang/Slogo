package controller;

import javafx.scene.paint.Color;

public class ModelTransformer {
	
	private MainController myController;
	
	//configuration variables should be here, i.e. background color
	private String myLanguage;
	private Color myBackgroundColor;
	private Color myPenColor;
	private String myTurtleImage;
	
	public ModelTransformer(MainController controller) {
		myController = controller;
	}

	public void transformOutputBox() {
		
		//thing that's inside node = myController.getMyView().getMyOutputBox() .get ???;
	}

	public void transformHistoryBox() {
		// TODO Auto-generated method stub
		
	}

	public void transformVariablesBox() {
		// TODO Auto-generated method stub
		
	}

	public void transformTurtleBox() {
		// TODO Auto-generated method stub
		
	}

	public void transformCommandsBox() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Updates what is visible in the Variables to the user
	 */
	private void updateVariablesBox() {
		
	}
	
	/**
	 * Updates what is visible in the Commands to the user
	 */
	private void updateCommandsBox() {
		
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
		
	public void setLanguage(String language) {
		myLanguage = language;
	}

	public void setBackgroundColor(Color color) {
		myBackgroundColor = color;
	}

	public void setPenColor(Color color) {
		myPenColor = color;
	}

	public void setTurtleImage(String image) {
		myTurtleImage = image;
	}
	
}
