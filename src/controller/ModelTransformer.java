
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
import model.UserCommands;
import model.turtle.Turtle;
import model.turtle.TurtleCoordinates;
import model.turtle.TurtlePlayground;
import view.panelelements.ColorsElement;
import view.panelelements.CommandsElement;
import view.panelelements.HistoryElement;
import view.panelelements.OutputElement;
import view.panelelements.PanelElement;
import view.panelelements.TurtleElement;
import view.panelelements.TurtleInfoElement;
import view.panelelements.VariablesElement;

/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

public class ModelTransformer {

	private static final double CENTER_X_COORDINATE = Constants.CENTER_X_COORDINATE;
	private static final double CENTER_Y_COORDINATE = Constants.CENTER_Y_COORDINATE;

	private MainController myController;

	private String myLanguage = Constants.getSpecification("DefaultLanguage");
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
	 * Clears previous elements within the ColorsElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param variables
	 */
	public void transformColorsElement(Map<Integer, Color> colors) {
		ColorsElement colorsElement = (ColorsElement) myController.getMyView().getColorsElement();
		ListView<String> colorIntegers = colorsElement.getIntegersListView();
		ListView<String> colorValues = colorsElement.getValuesListView();
		colorIntegers.getItems().clear();
		colorValues.getItems().clear();
		for (Integer i : colors.keySet()) {
			colorIntegers.getItems().add(Integer.toString(i));
			colorValues.getItems().add(colors.get(i).toString());
		}
	}

	/**
	 * Clears previous elements within the HistoryElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param map
	 */
	public void transformCommandsElement(UserCommands commands) {
		Map<String, List<String>> map = commands.getUserCommandMap();
		CommandsElement commandsElement = (CommandsElement) myController.getMyView().getMyCommandsElement();
		ListView<String> commandsValues = commandsElement.getListView();
		commandsValues.getItems().clear();
		for (String commandsItem : map.keySet()) {
			String parameters = "";
			for (String parameter : map.get(commandsItem)) {
				parameters += parameter + " ";
			}
			commandsValues.getItems().add(commandsItem + " " + parameters.trim());
		}

	}

	/**
	 * Clears previous elements within the TurtleInfoElement ListView and
	 * repopulates it with updated Model information
	 * 
	 * @param map
	 */
	public void transformTurtleInfoElement(List<Turtle> turtles) {
		TurtleInfoElement turtleInfoElement = (TurtleInfoElement) myController.getMyView().getMyTurtleInfoElement();
		ListView<String> turtleInfoValues = turtleInfoElement.getListView();

		turtleInfoValues.getItems().clear();
		for (Turtle t : turtles) {
			turtleInfoValues.getItems()
					.add(Integer.toString(t.getTurtleID()) + ": "
							+ Double.toString(Math.round(t.getCoordinate().getXCoord())) + ","
							+ Double.toString(Math.round(t.getCoordinate().getYCoord())) + ", "
							+ Double.toString(t.getOrientation()) + ", " + t.getPenDown());
		}
	}

	/**
	 * Reads all turtle-related information and updates relevant graphics
	 * 
	 * @param orientation
	 * @param queue
	 */
	public void transformTurtleGraphics(TurtlePlayground turtlePlayground) {
		myController.setBackgroundColor(turtlePlayground.getMyBackgroundColor());
		Turtle turtle = turtlePlayground.getCurrentTurtle();

		GraphicsContext turtlegraphics = myController.getMyView().getMyTurtleGraphics();
		turtlegraphics.setFill(turtle.getPenColor());
		turtlegraphics.setStroke(turtle.getPenColor());
		turtlegraphics.setLineWidth(turtle.getPenSize());

		updateTurtleGraphicsPosition(turtlegraphics, turtle);

	}

	/**
	 * Updates where the turtle (or turtles) has drawn and corresponding
	 * PanelElement
	 */
	private void updateTurtleGraphicsPosition(GraphicsContext gc, Turtle turtle) {
		LinkedList<TurtleCoordinates> coordinates = turtle.getCoordinates();
		Double orientation = turtle.getOrientation();
		myTurtleElement.setTurtleOrientation(orientation);

		double currentX = CENTER_X_COORDINATE;
		double currentY = CENTER_Y_COORDINATE;

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

	public String getLanguage() {
		return myLanguage;
	}

	public void setTurtleElement(PanelElement turtleElement) {
		myTurtleElement = (TurtleElement) turtleElement;
	}

}
