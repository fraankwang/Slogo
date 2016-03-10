/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.turtle;

import javafx.scene.paint.Color;

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
	private Color color;
	private Double penSize;


	
	public TurtleCoordinates(Double xCoordinate, Double yCoordinate) {
		xCoord = xCoordinate;
		yCoord = yCoordinate;
	}
	
	public TurtleCoordinates(Double xCoordinate, Double yCoordinate, boolean pen, Color newColor, Double newPenSize) {
		this(xCoordinate,yCoordinate);
		penDown = pen;
		color=newColor;
		penSize= newPenSize;
	}
	
	// =========================================================================
	// Getters and Setters
	// =========================================================================
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color newColor){
		color=newColor;
	}
	
	public Double getPenSize(){
		return penSize;
	}
	
	public void setPenSize(Double size){
		penSize = size;
	}
	
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
