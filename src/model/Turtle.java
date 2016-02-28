/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

public class Turtle {
	private Double xCoordinate;
	private Double yCoordinate;
	private boolean penDown;
	private boolean showTurtle;
	private Double Orientation;
	private Queue<TurtleCoordinates> turtleCoordinates;
	// other info like pen color and turtle image can be added later
	// Turtle should have some sort of identifier functionality. My friend said 
	// if we command turtle1 to move, then turtle2 to move, our code should be able to handle that.
	public Turtle() {
		this(0.0,0.0);
		
	}
	
	public Turtle(Double xCoord, Double yCoord){
		penDown = false;
		showTurtle = false;
		Orientation = 90.0;
		xCoordinate=xCoord;
		yCoordinate=yCoord;
		turtleCoordinates = new LinkedList<TurtleCoordinates>();
	}
	
	public Queue<TurtleCoordinates> getCoordinates(){
		return this.turtleCoordinates;
	}
	public void addCoordinates(Double xCoord, Double yCoord){
		TurtleCoordinates coordinate=new TurtleCoordinates(xCoord, yCoord);
		turtleCoordinates.add(coordinate);
	}

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
	
	public boolean isPenDown(){
		return this.penDown;
	}
	
	public boolean isVisible(){
		return this.showTurtle;
	}
	
	
}
