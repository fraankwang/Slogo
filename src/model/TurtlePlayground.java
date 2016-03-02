/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.LinkedList;

/**
 * The TurtlePlayground class represents the playground in which the turtle
 * moves around in Slogo. The TurtlePlayground class has an instance of a
 * Turtle, an an instance of the Width and Height. The turtle moves around
 * within the boundaries of the turtle playground.
 * 
 */
public class TurtlePlayground {
	private Turtle myTurtle;
	private int myWidth;
	private int myHeight;

	public TurtlePlayground() {
		myTurtle = new Turtle();
	}

	public TurtlePlayground(int width, int height) {
		myTurtle = new Turtle();
		myWidth = width;
		myHeight = height;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;

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

	public LinkedList<TurtleCoordinates> getTurtleCoordinates() {
		return (LinkedList<TurtleCoordinates>) myTurtle.getCoordinates();
	}

	public Turtle getTurtle() {
		return this.myTurtle;
	}

	/**
	 * The inBounds() method returns a boolean of whether the current turtle is
	 * within the boundaries of the playground.
	 *
	 */
	public boolean inBounds() {
		return inBounds(myTurtle.getxCoordinate(), myTurtle.getyCoordinate());
	}

	/**
	 * The inBounds() method takes in a coordinate pair and returns a boolean
	 * indicating whether the coordinate pair is within the boundaries of the
	 * playground.
	 *
	 */
	public boolean inBounds(Double xCoord, Double yCoord) {
		if (xCoord < -myWidth / 2 || xCoord > myWidth / 2 || yCoord < -myHeight / 2 || yCoord > myHeight / 2)
			return false;
		else
			return true;
	}

	/**
	 * The getDistance() method returns the distance between two coordinate
	 * points
	 * 
	 */
	public Double getDistance(Double xCoord1, Double yCoord1, Double xCoord2, Double yCoord2) {
		return Math.sqrt(Math.pow((xCoord1 - xCoord2), 2) + Math.pow((yCoord1 - yCoord2), 2));
	}

	/**
	 * The setTurtleCoordinates() method sets the Turtle's X and Y coordinate to
	 * a new coordinate pair as long as they are within bounds of the playground
	 * and returns the distance between its current location and its new
	 * coordinates. If not in bounds, the method returns 0.
	 */
	public Double setTurtleCoordinates(Double xCoord, Double yCoord, Double returnValue) {
		if (inBounds(xCoord, yCoord)) {
			myTurtle.setxCoordinate(xCoord);
			myTurtle.setyCoordinate(yCoord);
			myTurtle.addCoordinates(xCoord, yCoord);
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
	public Double placeTurtle(Double xCoord, Double yCoord) {
		Double distance = getDistance(myTurtle.getxCoordinate(), myTurtle.getyCoordinate(), xCoord, yCoord);
		return setTurtleCoordinates(xCoord, yCoord, distance);
	}

	/**
	 * The moveTurtle() method calculates the new x and y coordinates for a
	 * turtle given a number of pixels it must traverse. T
	 */
	public Double moveTurtle(Double pixels) {
		Double xCoord = myTurtle.getxCoordinate() + (pixels * Math.cos(Math.toRadians(myTurtle.getOrientation())));
		Double yCoord = myTurtle.getyCoordinate() + (pixels * Math.sin(Math.toRadians(myTurtle.getOrientation())));
		System.out.println(xCoord + " , " + yCoord);
		return setTurtleCoordinates(xCoord, yCoord, Math.abs(pixels));

	}

	/**
	 * The turnTurtle() method turns the turtle by a certain number of degrees
	 */
	public Double turnTurtle(Double degrees) {
		Double newDegree = myTurtle.getOrientation() - degrees;
		myTurtle.setOrientation(newDegree);
		return degrees;
	}

	/**
	 * The setTurtleHome() method sets the Turtle to its home position (0,0).
	 */
	public Double setTurtleHome() {
		return placeTurtle(0.0, 0.0);
	}

}
