/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.turtle;

/**
 * The TurtleCoordinates class represents a TurtleCoordinate object, which is
 * contained in the Turtle's LinkedList<TurtleCoordinates> turtleCoordinates. It
 * is used to keep track of which coordinates the pen is Down. The
 * TurtleCoordinates class has an instance of an xCoordinate, a yCoordinate, and
 * a boolean to indicate whether the pen is down at that coordinate pair
 * 
 */
public class TurtleCoordinates {

	private Double xCoord;
	private Double yCoord;
	private boolean penDown;

	public TurtleCoordinates(Double xCoordinate, Double yCoordinate, boolean p) {
		xCoord = xCoordinate;
		yCoord = yCoordinate;
		penDown = p;
	}
	
	public TurtleCoordinates(Double xCoordinate, Double yCoordinate) {
		xCoord = xCoordinate;
		yCoord = yCoordinate;
	}
	

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public Double getXCoord() {
		return xCoord;
	}

	public Double getYCoord() {
		return yCoord;
	}

	public boolean isPenDown() {
		return penDown;
	}
	
	public void setXCoord(Double xCoordinate){
		xCoord=xCoordinate;
	}
	
	public void setYCoord(Double yCoordinate){
		yCoord=yCoordinate;
	}
	
	public void setPenDown(Boolean pen){
		penDown=pen;
	}
	
	public void setCoordinates(Double xCoordinate, Double yCoordinate){
		setXCoord(xCoordinate);
		setYCoord(yCoordinate);
	}
}
