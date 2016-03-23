/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import constants.Constants;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.UserCommands;
import model.Variables;
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


public class ModelTransformer {

	private static final double CENTER_X_COORDINATE = Constants.CENTER_X_COORDINATE;
	private static final double CENTER_Y_COORDINATE = Constants.CENTER_Y_COORDINATE;

	private MainController myController;

	private String myLanguage = Constants.getSpecification("DefaultLanguage");
	private TurtleElement myTurtleElement;

	public ModelTransformer(MainController mainController) {
		myController = mainController;
	}

	/**
	 * Clears previous TextArea in OutputElement and repopulates it with updated
	 * Model information
	 * 
	 * @param output
	 */
	public void transformOutputElement(Queue<String> output) {
		OutputElement outputElement = (OutputElement) myController.getMyView().getMyActiveWorkspace().getMyOutputElement();
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
		HistoryElement historyElement = (HistoryElement) myController.getMyView().getMyActiveWorkspace().getMyHistoryElement();
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
	public void transformVariablesElement(Variables myVariables) {
		Map<String, Double> variables = myVariables.getVariableMap();
		VariablesElement variablesElement = (VariablesElement) myController.getMyView().getMyActiveWorkspace().getMyVariablesElement();
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
		ColorsElement colorsElement = (ColorsElement) myController.getMyView().getMyActiveWorkspace().getMyColorsElement();
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
		CommandsElement commandsElement = (CommandsElement) myController.getMyView().getMyActiveWorkspace().getMyCommandsElement();
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
		TurtleInfoElement turtleInfoElement = (TurtleInfoElement) myController.getMyView().getMyActiveWorkspace().getMyTurtleInfoElement();
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
	 * Grabs current turtle information and displays it in given TextArea
	 * 
	 * @param textArea
	 */
	public void displayTurtleInfo(TextArea textArea) {
		String language = "Language: " + myController.getMyActiveModel().getLanguage() + "\n";
		String ID = "Turtle ID: " + Integer.toString(myController.getMyActiveModel().getMyPlayground().getCurrentTurtleID()) + "\n";
		String orientation = "Orientation: " + myController.getMyActiveModel().getMyPlayground().getCurrentTurtle().getOrientation() % 360
				+ "\n";

		String penUp;
		if (myController.getMyActiveModel().getMyPlayground().getCurrentTurtle().getPenDown()) {
			penUp = "Pen is: down" + "\n";
		} else {
			penUp = "Pen is: up" + "\n";
		}

		String penColor = "Pen color: " + myController.getMyActiveModel().getMyPlayground().getCurrentPenColor() + "\n";

		Double xCoord = myController.getMyView().getMyActiveWorkspace().getMyTurtleElement().getNode().getTranslateX();
		Double yCoord = myController.getMyView().getMyActiveWorkspace().getMyTurtleElement().getNode().getTranslateY();
		if (yCoord != 0) {
			yCoord *= -1;
		}
		String x = "X Coordinate: " + Double.toString(xCoord) + "\n";
		String y = "Y Coordinate: " + Double.toString(yCoord);
		textArea.setText(language + ID + orientation + penUp + penColor + x + y);

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
		
		GraphicsContext turtleGraphics = myController.getMyView().getMyActiveWorkspace().getMyTurtleGraphics();
		turtleGraphics.setFill(turtle.getPenColor());
		turtleGraphics.setStroke(turtle.getPenColor());
		turtleGraphics.setLineWidth(turtle.getPenWidth());

		updateTurtleGraphicsPosition(turtleGraphics, turtlePlayground);

	}

	/**
	 * Updates where the turtle (or turtles) has drawn and corresponding
	 * PanelElement
	 */
	private void updateTurtleGraphicsPosition(GraphicsContext gc, TurtlePlayground playground) {
		
		Turtle turtle = playground.getCurrentTurtle();
		LinkedList<TurtleCoordinates> coordinates = turtle.getCoordinates();
		List<TurtleCoordinates> stampCoordinates = playground.getStampCoordinates();
		Double orientation = turtle.getOrientation();
		myTurtleElement.setTurtleOrientation(orientation);

		double currentX = CENTER_X_COORDINATE;
		double currentY = CENTER_Y_COORDINATE;

		for (TurtleCoordinates coordinate : coordinates) {
			double newX = CENTER_X_COORDINATE + coordinate.getXCoord();
			double newY = CENTER_Y_COORDINATE + (-1 * coordinate.getYCoord());

			if (stampCoordinates.contains(coordinate)) {
				double stampX = CENTER_X_COORDINATE + coordinate.getXCoord();
				double stampY = CENTER_Y_COORDINATE + (-1 * coordinate.getYCoord());
				myTurtleElement.stamp(stampX, stampY);
			}
			
			if (turtle.isPenDown()) {
				gc.strokeLine(currentX, currentY, newX, newY);
			}

			Double rounded = (double) Math.round(newX);
			myTurtleElement.moveTurtleImage(rounded - CENTER_X_COORDINATE, -1 * coordinate.getYCoord());

			currentX = newX;
			currentY = newY;

		}
		
		if (playground.getStampCoordinates().isEmpty()) {
			myTurtleElement.clearStamps();
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

	public void setTurtleImage(String image) {
		TurtleElement myTurtleElement = (TurtleElement) myController.getMyView().getMyActiveWorkspace().getMyTurtleElement();
		StackPane myTurtleWrapper = myController.getMyView().getMyActiveWorkspace().getMyTurtleWrapper();
		Canvas myTurtlePlayground = myController.getMyView().getMyActiveWorkspace().getMyTurtlePlayground();

		ImageView newImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(image + ".jpg")));
		newImage.setFitWidth(Constants.TURTLE_ELEMENT_WIDTH);
		newImage.setFitHeight(Constants.TURTLE_ELEMENT_HEIGHT);
		Double oldX = myTurtleElement.getNode().getTranslateX();
		Double oldY = myTurtleElement.getNode().getTranslateY();
		((TurtleElement) myTurtleElement).setTurtleImage(newImage);
		myTurtleWrapper.getChildren().clear();
		myTurtleWrapper.getChildren().addAll(myTurtlePlayground, myTurtleElement.getNode());
		((TurtleElement) myTurtleElement).moveTurtleImage(oldX, oldY);
		((TurtleElement) myTurtleElement)
				.setTurtleOrientation(myController.getMyActiveModel().getMyPlayground().getCurrentTurtle().getOrientation());

		myController.getMyActiveModel().getMyPlayground().setCurrentTurtleShape(image);
	}

	/**
	 * Deletes stored previously run commands and moves turtle position back to
	 * start
	 */
	public void resetTurtlePosition() {
		myController.getMyView().getMyActiveWorkspace().getMyTurtleGraphics().clearRect(0, 0, Constants.PLAYGROUND_WIDTH,
				Constants.PLAYGROUND_HEIGHT);
		myController.getMyActiveModel().getMyPlayground().getCurrentTurtle().clearTurtleCoordinates();
		myController.getMyActiveModel().getMyPlayground().setTurtleHome();
		TurtleElement turtleElement = (TurtleElement) myController.getMyView().getMyActiveWorkspace().getMyTurtleElement();
		turtleElement.moveTurtleImage(0.0, 0.0);
	}

}
