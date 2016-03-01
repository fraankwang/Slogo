package controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import model.History;
import model.MainModel;
import model.Variables;
import view.CommandsElement;
import view.HistoryElement;
import view.OutputElement;
import view.VariablesElement;

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

	public void transformOutputElement(Queue<String> output) {
		OutputElement outputElement = (OutputElement) myController.getMyView().getMyOutputElement();
		TextArea outputText = outputElement.getText();
		String displayText = "";
		for(String outputItem : output){
			displayText += outputItem + "\n";
		}
		outputText.clear();
		outputText.setText(displayText);
	}

	public void transformHistoryElement(Queue<String> history) {
		HistoryElement historyElement = (HistoryElement) myController.getMyView().getMyHistoryElement();
		ListView<String> historyValues = historyElement.getListView();
		historyValues.getItems().clear();
		for(String historyItem : history){
			historyValues.getItems().add(historyItem);
		}
	}

	public void transformVariablesElement(Map<String, Double> variables) {
		VariablesElement variablesElement = (VariablesElement) myController.getMyView().getMyVariablesElement();
		ListView<String> variablesValues = variablesElement.getListView();
		variablesValues.getItems().clear();
		for(String variablesItem : variables.keySet()){
			variablesValues.getItems().add(variablesItem);
		}
	}

	public void transformTurtleGraphics() {
		GraphicsContext tb = myController.getMyView().getMyTurtleGraphics();
		updateTurtleGraphics(tb);
		
	}

	public void transformCommandsElement(Map<String, String> commands) {
		CommandsElement commandsElement = (CommandsElement) myController.getMyView().getMyCommandsElement();
		ListView<String> commandsValues = commandsElement.getListView();
		commandsValues.getItems().clear();
		for(String commandsItem : commands.keySet()){
			commandsValues.getItems().add(commandsItem);
		}
		
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
