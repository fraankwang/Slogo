package model;

import java.util.HashMap;
import java.util.Map;
import constants.Constants;
import javafx.scene.paint.Color;
import model.turtle.TurtlePlayground;

public class Configuration {

	private Color myBackgroundColor = Constants.DEFAULT_BACKGROUND_COLOR;
	private Color myPenColor = Constants.DEFAULT_PEN_COLOR;
	private Map<Integer, Color> myPenColors;
	private Map<Integer, Double> myPenSize;
	private Double myCurrentPenSize;
	private Map<Integer, String> myTurtleShapes;

	public Configuration() {
		myCurrentPenSize = Constants.DEFAULT_TURTLE_PEN_WIDTH;
		myPenColors = new HashMap<Integer, Color>();
		myTurtleShapes = new HashMap<Integer, String>();
	}

	public void setBackgroundColor(Color backgroundColor) {
		myBackgroundColor = backgroundColor;
	}

	public Double getCurrentPenSize(TurtlePlayground playground) {
		int turtleID = playground.getCurrentTurtleID();
		Double currentSize;
		if (myPenSize.keySet().contains(turtleID)) {
			currentSize = myPenSize.get(turtleID);
		} else {
			currentSize = Constants.DEFAULT_TURTLE_PEN_WIDTH;
		}
		return currentSize;
	}

	public Color getBackgroundColor() {
		return myBackgroundColor;
	}

	public void addPenSize(Integer index, Double size) {
		myPenSize.put(index, size);
	}

	public void addPenColor(Integer index, Color color) {
		myPenColors.put(index, color);
	}

	public Color getPenColor(Integer index) {
		return myPenColors.get(index);
	}

	public void addTurtleShape(Integer index, String shape) {
		myTurtleShapes.put(index, shape);
	}

	public String getTurtleShape(Integer index) {
		return myTurtleShapes.get(index);
	}

	public String returnTurtleShape(Integer index) {
		return myTurtleShapes.get(index);
	}

	public void setPenColor(Color color) {
		myPenColor = color;
	}

	public Color getPenColor() {
		return myPenColor;
	}

}
