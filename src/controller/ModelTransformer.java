package controller;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelTransformer {
	
	private static final int CENTER_X_COORDINATE = 350;
	private static final int CENTER_Y_COORDINATE = 275;

	private MainController myController;
	
	private String myLanguage = Constants.getSpecification("DefaultLanguage");
	private Color myPenColor;
	private String myTurtleImage;
	
	public ModelTransformer(MainController controller) {
		myController = controller;
	}

	public void transformOutputElement() {
		
		//thing that's inside node = myController.getMyView().getMyOutputElement() .get ???;
	}

	public void transformHistoryElement() {
		// TODO Auto-generated method stub
		
	}

	public void transformVariablesElement() {
		// TODO Auto-generated method stub
		
	}

	public void transformTurtleGraphics() {
		GraphicsContext tb = myController.getMyView().getMyTurtleGraphics();
		updateTurtleGraphics(tb);
		
	}

	public void transformCommandsElement() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Updates what is visible in the Variables to the user
	 */
	private void updateVariablesElement() {
		
	}
	
	/**
	 * Updates what is visible in the Commands to the user
	 */
	private void updateCommandsElement() {
		
	}
	
	/**
	 * Updates what is visible in the console to the user
	 */
	private void updateOutputElement() {
		// modify elements within outputbox so we don't have to deal with actual
		// UI wrapper
		// myView.getMyOutputElement().text or whatever
	}

	/**
	 * Updates what is visible in the history to the user
	 */
	private void updateHistoryElement() {
		// myView.getMyHistoryElement() and modify element within it so we don't
		// deal with actual UI wrapper
	}

	/**
	 * Updates where the turtle (or turtles) has drawn
	 */
	private void updateTurtleGraphics(GraphicsContext gc) {
				
		gc.setFill(myPenColor);
		gc.setStroke(myPenColor);
		gc.setLineWidth(5);

		gc.strokeLine(CENTER_X_COORDINATE, CENTER_Y_COORDINATE, CENTER_X_COORDINATE + 100, CENTER_Y_COORDINATE + 100);
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================
		
	public void setLanguage(String language) {
		myLanguage = language;
	}

	public void setPenColor(Color color) {
		myPenColor = color;
	}

	public void setTurtleImage(String image) {
		myTurtleImage = image;
	}

	public String getLanguage() {
		return myLanguage;
	}
	
}
