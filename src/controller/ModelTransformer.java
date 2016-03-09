
package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import model.Configuration;
import model.turtle.Turtle;
import model.turtle.TurtleCoordinates;
import view.panelelements.CommandsElement;
import view.panelelements.HistoryElement;
import view.panelelements.OutputElement;
import view.panelelements.PanelElement;
import view.panelelements.TurtleElement;
import view.panelelements.VariablesElement;

/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

public class ModelTransformer {

	private static final double CENTER_X_COORDINATE = Constants.CENTER_X_COORDINATE;
	private static final double CENTER_Y_COORDINATE = Constants.CENTER_Y_COORDINATE;

	private MainController myController;

	private String myLanguage = Constants.getSpecification("DefaultLanguage");
	private Color myPenColor;
	private Double myPenWidth = Constants.DEFAULT_TURTLE_PEN_WIDTH;
	private TurtleElement myTurtleElement;

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

	public void transformColorElement() {
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
		ListView<String> variablesNames = variablesElement.getNamesListView();
		ListView<String> variablesValues = variablesElement.getValuesListView();
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
			for (String parameter : map.get(commandsItem)) {
				parameters += parameter + " ";
			}
			commandsValues.getItems().add(commandsItem + " (" + parameters.trim() + ")");
		}

	}

	/**
	 * Reads TurtleCoordinate from queue and draws new line
	 * 
	 * @param orientation
	 * 
	 * @param queue
	 */
	public void transformTurtleGraphics(Turtle turtle, Configuration configs) {
		GraphicsContext playground = myController.getMyView().getMyTurtleGraphics();
		updateTurtleGraphics(playground, turtle, configs);

	}

	/**
	 * Updates where the turtle (or turtles) has drawn
	 */
	private void updateTurtleGraphics(GraphicsContext gc, Turtle turtle, Configuration configs) {
		LinkedList<TurtleCoordinates> coordinates = turtle.getCoordinates();
		Double orientation = turtle.getOrientation();
		myTurtleElement.setTurtleOrientation(orientation);
		
		double currentX = CENTER_X_COORDINATE;
		double currentY = CENTER_Y_COORDINATE;
		gc.setFill(configs.getPenColor());
		gc.setStroke(configs.getPenColor());
		gc.setLineWidth(configs.getPenCurrentSize());

		for (TurtleCoordinates coordinate : coordinates) {
			double newX = CENTER_X_COORDINATE + coordinate.getXCoord();
			double newY = CENTER_Y_COORDINATE + (-1 * coordinate.getYCoord());
			if (turtle.isPenDown()) {
				gc.strokeLine(currentX, currentY, newX, newY);

			}

			Double rounded = (double) Math.round(newX);
			myTurtleElement.moveTurtleImage(rounded - CENTER_X_COORDINATE, -1 * coordinate.getYCoord());


			currentX = newX;
			currentY = newY;

		}

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

	public void setPenWidth(double pixels) {
		myPenWidth = pixels;
	}

	public String getLanguage() {
		return myLanguage;
	}

	public void setTurtleElement(PanelElement turtleElement) {
		myTurtleElement = (TurtleElement) turtleElement;
	}

}
