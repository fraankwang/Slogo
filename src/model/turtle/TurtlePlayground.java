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
	private List<Turtle> myTurtleList;
	private Turtle myCurrentTurtle;
	private double myWidth;
	private double myHeight;
	// private ArrayList<Turtle> turtleList;
	// saved for later

	public TurtlePlayground() {
		myCurrentTurtle = new Turtle();
		myTurtleList=new ArrayList<Turtle>();
		myTurtleList.add(myCurrentTurtle);		
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
	
	public void setCurrentTurtle(Turtle turtle){
		this.myCurrentTurtle=turtle;
	}

	public Turtle getCurrentTurtle() {
		return this.myCurrentTurtle;
	}
	
	public List<Turtle> getTurtleList(){
		return this.myTurtleList;
	}
	
	public Turtle getTurtle(int index){
		Turtle turtle=getTurtleList().get(index);
		setCurrentTurtle(turtle);
		return turtle;
	}
	
	public void addTurtle(Turtle turtle){
		getTurtleList().add(turtle);
		setCurrentTurtle(turtle);
	}
	
	public int getTurtleID(Turtle turtle){
		return getTurtleList().indexOf(turtle);
	}


	/**
	 * The inBounds() method returns a boolean of whether the current turtle is
	 * within the boundaries of the playground.
	 *
	 */
	public boolean inBounds() {
		return inBounds(myCurrentTurtle.getxCoordinate(), myCurrentTurtle.getyCoordinate());
	}

	/**
	 * The inBounds() method takes in a coordinate pair and returns a boolean
	 * indicating whether the coordinate pair is within the boundaries of the
	 * playground.
	 *
	 */
	public boolean inBounds(Double xCoord, Double yCoord) {
		return !(xCoord < -myWidth / 2 || xCoord > myWidth / 2 || yCoord < -myHeight / 2 || yCoord > myHeight / 2);

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
			myCurrentTurtle.setxCoordinate(xCoord);
			myCurrentTurtle.setyCoordinate(yCoord);
			myCurrentTurtle.addCoordinates(xCoord, yCoord);
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
		Double distance = getDistance(myCurrentTurtle.getxCoordinate(), myCurrentTurtle.getyCoordinate(), xCoord, yCoord);
		return setTurtleCoordinates(xCoord, yCoord, distance);
	}

	/**
	 * The moveTurtle() method calculates the new x and y coordinates for a
	 * turtle given a number of pixels it must traverse. T
	 */
	public Double moveTurtle(Double pixels) {
		Double xCoord = myCurrentTurtle.getxCoordinate() + (pixels * Math.sin(Math.toRadians(myCurrentTurtle.getOrientation())));
		Double yCoord = myCurrentTurtle.getyCoordinate() + (pixels * Math.cos(Math.toRadians(myCurrentTurtle.getOrientation())));
		System.out.println(xCoord + " , " + yCoord);
		return setTurtleCoordinates(xCoord, yCoord, Math.abs(pixels));

	}

	/**
	 * The turnTurtle() method turns the turtle by a certain number of degrees
	 */
	public Double turnTurtle(Double degrees) {
		Double newDegree = myCurrentTurtle.getOrientation() + degrees;
		myCurrentTurtle.setOrientation(newDegree);
		return degrees;
	}

	/**
	 * The setTurtleHome() method sets the Turtle to its home position (0,0).
	 */
	public Double setTurtleHome() {
		myCurrentTurtle.setOrientation(0.0);
		return placeTurtle(0.0, 0.0);
	}

	public LinkedList<TurtleCoordinates> getTurtleCoordinates() {
		return (LinkedList<TurtleCoordinates>) myCurrentTurtle.getCoordinates();
	}

	public Double getTurtleOrientation() {
		return myCurrentTurtle.getOrientation();
	}

}
