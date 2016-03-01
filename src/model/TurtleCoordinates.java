package model;

public class TurtleCoordinates {

	private Double xCoord;
	private Double yCoord;
	private boolean penDown;
	
	public TurtleCoordinates(Double xCoordinate, Double yCoordinate, boolean p){
		xCoord=xCoordinate;
		yCoord=yCoordinate;
		penDown = p;
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
	
}
