package controller;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import model.TurtleCoordinates;
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

	/**
	 * Clears previous TextArea in OutputElement and repopulates it with updated
	 * Model information
	 * 
	 * @param output
	 */
	public void transformOutputElement(Queue<String> output) {
		OutputElement outputElement = (OutputElement) myController.getMyView().getMyOutputElement();
		TextArea textArea = outputElement.getText();
		String newOutput = "";
		for (String outputItem : output) {
			newOutput += outputItem + "\n";
		}
		textArea.clear();
		textArea.setText(newOutput);
	}

	/**
	 * Clears previous elements within the HistoryElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param history
	 */
	public void transformHistoryElement(Queue<String> history) {
		HistoryElement historyElement = (HistoryElement) myController.getMyView().getMyHistoryElement();
		ListView<String> historyValues = historyElement.getListView();
		historyValues.getItems().clear();
		for (String historyItem : history) {
			historyValues.getItems().add(historyItem);
		}
	}

	/**
	 * Clears previous elements within the HistoryElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param variables
	 */
	public void transformVariablesElement(Map<String, Double> variables) {
		VariablesElement variablesElement = (VariablesElement) myController.getMyView().getMyVariablesElement();
		ListView<String> variablesNames = variablesElement.getListView().get(0);
		ListView<String> variablesValues = variablesElement.getListView().get(1);
		variablesNames.getItems().clear();
		variablesValues.getItems().clear();
		for (String variablesItem : variables.keySet()) {
			variablesNames.getItems().add(variablesItem);
			variablesValues.getItems().add(variables.get(variablesItem).toString());
		}
	}

	/**
	 * Clears previous elements within the HistoryElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param map
	 */
	public void transformCommandsElement(Map<String, List<String>> map) {
		CommandsElement commandsElement = (CommandsElement) myController.getMyView().getMyCommandsElement();
		ListView<String> commandsValues = commandsElement.getListView();
		commandsValues.getItems().clear();
		for (String commandsItem : map.keySet()) {
			String parameters = "";
			for(String parameter : map.get(commandsItem)){
				parameters += parameter + " ";
			}
			commandsValues.getItems().add(commandsItem + " (" + parameters.trim() + ")");
		}
		
	}
	
	/**
	 * Reads TurtleCoordinate from queue and draws new line
	 * @param queue
	 */
	public void transformTurtleGraphics(Queue<TurtleCoordinates> queue) {
		GraphicsContext tb = myController.getMyView().getMyTurtleGraphics();
		updateTurtleGraphics(tb);

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
