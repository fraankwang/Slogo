/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.turtle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The TurtlePlayground class represents the playground in which the turtle
 * moves around in Slogo. The TurtlePlayground class has an instance of a
 * Turtle, an an instance of the Width and Height. The turtle moves around
 * within the boundaries of the turtle playground.
 * 
 */
public class TurtlePlayground {
	private List<Turtle> myTurtles;
	private List<Turtle> myActiveTurtles;
	private Turtle myCurrentTurtle;
	private double myWidth;
	private double myHeight;

	public TurtlePlayground() {
		myCurrentTurtle = new Turtle(1);
		myTurtles = new ArrayList<Turtle>();
		myActiveTurtles = new ArrayList<Turtle>();
		myTurtles.add(myCurrentTurtle);
		myActiveTurtles.add(myCurrentTurtle);
	}

	public TurtlePlayground(double width, double height) {
		this();
		myWidth = width;
		myHeight = height;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public void setWidth(int width) {
		myWidth = width;
	}

	public void setHeight(int height) {
		myHeight = height;
	}

	public void setCurrentTurtle(Turtle turtle) {
		this.myCurrentTurtle = turtle;
	}

	public Turtle getCurrentTurtle() {
		return this.myCurrentTurtle;
	}

	public List<Turtle> getActiveTurtles() {
		return this.myActiveTurtles;
	}

	public List<Turtle> getTurtleList() {
		return this.myTurtles;
	}

	public Turtle getTurtle(int index) {
		for (Turtle turtle : getTurtleList()) {
			if (turtle.getTurtleID() == index) {
				setCurrentTurtle(turtle);
				return turtle;
			}
		}
		return addTurtle(index);
		
	}

	public int getCurrentTurtleID() {
		return getCurrentTurtle().getTurtleID();
	}

	public Turtle addTurtle(int index) {
		Turtle turtle = new Turtle(index);
		getTurtleList().add(turtle);
		setCurrentTurtle(turtle);
		return turtle;
	}

	/**
	 * The inBounds() method returns a boolean of whether the current turtle is
	 * within the boundaries of the playground.
	 *
	 */
	public boolean inBounds() {
		return inBounds(myCurrentTurtle.getCoordinate());
	}

	/**
	 * The inBounds() method takes in a coordinate pair and returns a boolean
	 * indicating whether the coordinate pair is within the boundaries of the
	 * playground.
	 *
	 */

	public boolean inBounds(TurtleCoordinates coordinate) {
		return !(coordinate.getXCoord() < -myWidth / 2 || coordinate.getXCoord() > myWidth / 2
				|| coordinate.getYCoord() < -myHeight / 2 || coordinate.getYCoord() > myHeight / 2);

	}

	/**
	 * The getDistance() method returns the distance between two coordinate
	 * points
	 * 
	 */

	public Double getDistance(TurtleCoordinates coordinate1, TurtleCoordinates coordinate2) {
		return Math.sqrt(Math.pow((coordinate1.getXCoord() - coordinate2.getXCoord()), 2)
				+ Math.pow((coordinate1.getYCoord() - coordinate2.getYCoord()), 2));

	}

	/**
	 * The setTurtleCoordinates() method sets the Turtle's X and Y coordinate to
	 * a new coordinate pair as long as they are within bounds of the playground
	 * and returns the distance between its current location and its new
	 * coordinates. If not in bounds, the method returns 0.
	 */

	public Double setTurtleCoordinates(TurtleCoordinates coordinates, Double returnValue) {
		if (inBounds(coordinates)) {
			myCurrentTurtle.setCoordinate(coordinates);
			myCurrentTurtle.addCoordinates(coordinates);
			return returnValue;
		} else
			return (double) 0;
	}

	/**
	 * The placeTurtle() method calculates the distance between the turtle's
	 * current coordinate pair and a new coordinate pair. The placeTurtle()
	 * method calls the setTurtleCoordinates() method in order to place the
	 * turtle into a new coordinate location given an X and Y coordinate.
	 * 
	 */
	public Double placeTurtle(Double xCoordinate, Double yCoordinate) {
		Double returnVal = 0.0;
		for (Turtle turtle : getActiveTurtles()) {
			TurtleCoordinates coordinate = new TurtleCoordinates(xCoordinate, yCoordinate, turtle.getPenDown());
			setCurrentTurtle(turtle);
			Double distance = getDistance(myCurrentTurtle.getCoordinate(), coordinate);
			returnVal = distance;
			setTurtleCoordinates(coordinate, distance);
		}
		return returnVal;
	}

	public Double placeTurtle(TurtleCoordinates coordinate) {
		return placeTurtle(coordinate.getXCoord(), coordinate.getYCoord());
	}

	/**
	 * The moveTurtle() method calculates the new x and y coordinates for a
	 * turtle given a number of pixels it must traverse. T
	 */
	public Double moveTurtle(Double pixels) {
		for (Turtle turtle : getActiveTurtles()) {
			setCurrentTurtle(turtle);
			Double xCoord = myCurrentTurtle.getCoordinate().getXCoord()
					+ (pixels * Math.sin(Math.toRadians(myCurrentTurtle.getOrientation())));
			Double yCoord = myCurrentTurtle.getCoordinate().getYCoord()
					+ (pixels * Math.cos(Math.toRadians(myCurrentTurtle.getOrientation())));
			TurtleCoordinates coordinate = new TurtleCoordinates(xCoord, yCoord, turtle.getPenDown());

			setTurtleCoordinates(coordinate, Math.abs(pixels));
		}
		return pixels;
	}

	/**
	 * The turnTurtle() method turns the turtle by a certain number of degrees
	 */
	public Double turnTurtle(Double degrees) {
		for (Turtle turtle : getActiveTurtles()) {
			setCurrentTurtle(turtle);
			Double newDegree = myCurrentTurtle.getOrientation() + degrees;
			myCurrentTurtle.setOrientation(newDegree);

		}
		return degrees;
	}

	/**
	 * The setTurtleHome() method sets the Turtle to its home position (0,0).
	 */
	public Double setTurtleHome() {
		Double returnVal = 0.0;

		for (Turtle turtle : getActiveTurtles()) {
			setCurrentTurtle(turtle);
			myCurrentTurtle.setOrientation(0.0);
			TurtleCoordinates coordinate = new TurtleCoordinates(0.0, 0.0, turtle.getPenDown());
			returnVal = placeTurtle(coordinate);

		}
		return returnVal;
	}

	public LinkedList<TurtleCoordinates> getTurtleCoordinates() {
		return (LinkedList<TurtleCoordinates>) myCurrentTurtle.getCoordinates();
	}

	public Double getTurtleOrientation() {
		return myCurrentTurtle.getOrientation();
	}

}
