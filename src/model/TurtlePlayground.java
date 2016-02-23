/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

public class TurtlePlayground {
	private Turtle myTurtle;
	private int myWidth;
	private int myHeight;

	public TurtlePlayground(){

	}

	public void setTurtle (Turtle turtle) {
		myTurtle = turtle;

	}

	public void setWidth (int width) {
		myWidth = width;
	}

	public void setHeight (int height) {
		myHeight = height;
	}

	public boolean inBounds(){
		if(myTurtle.getxCoordinate()<0 || myTurtle.getxCoordinate()>myWidth || myTurtle.getyCoordinate()<0 || myTurtle.getyCoordinate()>myHeight) return false;
		else return true;
	}

	public boolean inBounds(int xCoord, int yCoord){
		if(xCoord<0 || xCoord>myWidth || yCoord<0 || yCoord>myHeight) return false;
		else return true;
	}
	
	public int getDistance(int xCoord1, int yCoord1, int xCoord2, int yCoord2){
		return (int) Math.sqrt( Math.pow((xCoord1-xCoord2),2) + Math.pow((yCoord1-yCoord2),2));
	}

	public int setTurtleCoordinates(int xCoord, int yCoord, int returnValue){
		if(inBounds(xCoord,yCoord)){
			myTurtle.setxCoordinate(xCoord);
			myTurtle.setyCoordinate(yCoord);
			return returnValue;
		}
		else return 0;
	}
	public int placeTurtle(int xCoord, int yCoord){
		int distance=getDistance(myTurtle.getxCoordinate(), myTurtle.getyCoordinate(), xCoord, yCoord);
		return setTurtleCoordinates(xCoord,yCoord, distance);
	}

	public int moveTurtle(int pixels){
		int xCoord=myTurtle.getxCoordinate() + (int) (pixels*Math.cos(Math.toRadians(myTurtle.getOrientation())));
		int yCoord=myTurtle.getyCoordinate() + (int) (pixels*Math.cos(Math.toRadians(myTurtle.getOrientation())));
		return setTurtleCoordinates(xCoord,yCoord,Math.abs(pixels));

	}

	public float turnTurtle(float degrees){
		myTurtle.setOrientation(degrees);
		return degrees;
	}
	
	public int setTurtleHome(){
		return placeTurtle(0,0);
	}
	//methods relating to moving the Turtle around in the playground
}
