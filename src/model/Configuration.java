package model;

import java.util.HashMap;
import java.util.Map;
import constants.Constants;
import javafx.scene.paint.Color;

public class Configuration {

	private Color background;
	private Map<Integer, Color> penColor;
	private Map<Integer, Double> penSize;
	private Map<Integer, String> turtleShape;

	public Configuration() {
		background = Constants.DEFAULT_BACKGROUND_COLOR;
		penColor = new HashMap<Integer, Color>();
		penSize = new HashMap<Integer, Double>();
		turtleShape = new HashMap<Integer, String>();
	}

	public void setBackgroundColor(Color backgroundColor) {
		background = backgroundColor;
	}

	public Color getBackgroundColor() {
		return background;
	}

	public void addPenColor(Integer index, Color color) {
		penColor.put(index, color);
	}

	public Color getPenColor(Integer index) {
		return penColor.get(index);
	}

	public void addPenSize(Integer index, Double size) {
		penSize.put(index, size);
	}

	public Double getPenSize(Integer index) {
		return penSize.get(index);
	}

	public void addTurtleShape(Integer index, String shape) {
		turtleShape.put(index, shape);
	}

	public String getTurtleShape(Integer index) {
		return turtleShape.get(index);
	}
	
	public String returnTurtleShape(Integer index) {
		return turtleShape.get(index);
	}
}
