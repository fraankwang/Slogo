package model;

import java.util.HashMap;
import java.util.Map;
import constants.Constants;
import javafx.scene.paint.Color;

public class Configuration {

	private Color myBackgroundColor;
	private Color myPenColor;
	private Map<Integer, Color> myPenColors;
	private Double myPenWidth;
	private Map<Integer, String> myTurtleShapes;

	public Configuration() {
		myBackgroundColor = Constants.DEFAULT_BACKGROUND_COLOR;
		myPenWidth = Constants.DEFAULT_TURTLE_PEN_WIDTH;
		myPenColors = new HashMap<Integer, Color>();
		myTurtleShapes = new HashMap<Integer, String>();
	}

	public void setBackgroundColor(Color backgroundColor) {
		myBackgroundColor = backgroundColor;
	}

	public Color getBackgroundColor() {
		return myBackgroundColor;
	}

	public void addPenColor(Integer index, Color color) {
		myPenColors.put(index, color);
	}

	public Color getPenColor(Integer index) {
		return myPenColors.get(index);
	}

	public void setPenWidth(Double size) {
		myPenWidth = size;
	}

	public Double getPenWidth() {
		return myPenWidth;
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
