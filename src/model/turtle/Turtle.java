/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.turtle;

import java.util.*;

/**
 * The Turtle class represents the turtle object within the turtle playground,.
 * The Turtle class has an instance of xCoordinate and yCoordinate, a boolean
 * for whether the pen is down, a boolean for whether the turtle should show on
 * the playground, a Double representing its orientation, and a
 * LinkedList<Coordinates representing the Turtle's moved coordinates, where
 * each coordinate object keeps track of whether the pen is down.
 */
public class Turtle {
	private Double xCoordinate;
	private Double yCoordinate;
	private boolean penDown;
	private boolean showTurtle;
	private Double Orientation;
	private LinkedList<TurtleCoordinates> turtleCoordinates;

	public Turtle() {
		this(0.0, 0.0);
	}

	public Turtle(Double xCoord, Double yCoord) {
		penDown = true;
		showTurtle = false;
		Orientation = 0.0;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		turtleCoordinates = new LinkedList<TurtleCoordinates>();
	}

	/**
	 * The addCoordinates() method adds a coordinate object to the turtle's
	 * turtleCoordinates instance variable, given an x and y coordinate.
	 *
	 */
	public void addCoordinates(Double xCoord, Double yCoord) {
		TurtleCoordinates coordinate = new TurtleCoordinates(xCoord, yCoord, penDown);
		turtleCoordinates.add(coordinate);
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public void setPenDown(boolean down) {
		this.penDown = down;
	}

	public void setShowTurtle(boolean show) {
		this.showTurtle = show;
	}

	public void setOrientation(Double orientation) {
		this.Orientation = orientation;
	}

	public Double getOrientation() {
		return this.Orientation;
	}

	public Double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Double yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public LinkedList<TurtleCoordinates> getCoordinates() {
		return this.turtleCoordinates;
	}

	public boolean isPenDown() {
		return this.penDown;
	}

	public boolean isVisible() {
		return this.showTurtle;
	}

	public void clearTurtleCoordinates() {
		turtleCoordinates = new LinkedList<TurtleCoordinates>();
	}

}
