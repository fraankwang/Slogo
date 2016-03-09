/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.turtle;

import java.util.*;

/**
 * The Turtle class represents the turtle object within the turtle playground.
 * The Turtle class has an instance of xCoordinate and yCoordinate, a boolean
 * for whether the pen is down, a boolean for whether the turtle should show on
 * the playground, a Double representing its orientation, and a
 * LinkedList<Coordinates representing the Turtle's moved coordinates, where
 * each coordinate object keeps track of whether the pen is down.
 */
public class Turtle {
	private int turtleID;
	private TurtleCoordinates turtleCoordinate;
	private boolean penDown;
	private boolean showTurtle;
	private Double Orientation;
	private LinkedList<TurtleCoordinates> turtleCoordinates;

	public Turtle(int turtleID) {
		this(0.0, 0.0, turtleID);
	}

	public Turtle(Double xCoord, Double yCoord, int turtleID) {
		penDown = true;
		showTurtle = false;
		Orientation = 0.0;
		turtleCoordinate = new TurtleCoordinates(xCoord, yCoord, penDown);
		turtleCoordinates = new LinkedList<TurtleCoordinates>();
		this.turtleID = turtleID;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public int getTurtleID() {
		return this.turtleID;
	}

	public void setPenDown(boolean down) {
		this.penDown = down;
	}

	public boolean getPenDown() {
		return this.penDown;
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

	public TurtleCoordinates getCoordinate() {
		return this.turtleCoordinate;
	}

	public void setCoordinate(TurtleCoordinates coordinate) {
		this.turtleCoordinate = coordinate;
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

	/**
	 * The addCoordinates() method adds a coordinate object to the turtle's
	 * turtleCoordinates instance variable, given an x and y coordinate.
	 *
	 */
	public void addCoordinates(TurtleCoordinates coordinate) {
		turtleCoordinates.add(coordinate);
	}

}
