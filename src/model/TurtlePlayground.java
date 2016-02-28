/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.ArrayList;

public class TurtlePlayground {
	private Turtle myTurtle;
	private int myWidth;
	private int myHeight;
	//private ArrayList<Turtle> turtleList;
	// saved for later

	public TurtlePlayground (){
		myTurtle=new Turtle();
		
		
	}

	public TurtlePlayground (int width, int height){
		myTurtle=new Turtle();
		myWidth=width;
		myHeight=height;
	}

	
	public void setTurtle (Turtle turtle) {
		myTurtle = turtle;

	}

	public Turtle getTurtle(){
		return this.myTurtle;
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

	public boolean inBounds(Double xCoord, Double yCoord){
		if(xCoord<0 || xCoord>myWidth || yCoord<0 || yCoord>myHeight) return false;
		else return true;
	}

	public Double getDistance(Double xCoord1, Double yCoord1, Double xCoord2, Double yCoord2){
		return Math.sqrt( Math.pow((xCoord1-xCoord2),2) + Math.pow((yCoord1-yCoord2),2));
	}

	public Double setTurtleCoordinates(Double xCoord, Double yCoord, Double returnValue){
		if(inBounds(xCoord,yCoord)){
			myTurtle.setxCoordinate(xCoord);
			myTurtle.setyCoordinate(yCoord);
			myTurtle.addCoordinates(xCoord, yCoord);
			return returnValue;
		}
		else return (double) 0;
	}
	public Double placeTurtle(Double xCoord, Double yCoord){
		Double distance=getDistance(myTurtle.getxCoordinate(), myTurtle.getyCoordinate(), xCoord, yCoord);
		return setTurtleCoordinates(xCoord,yCoord, distance);
	}

	public Double moveTurtle(Double pixels){
		Double xCoord=myTurtle.getxCoordinate() + (pixels*Math.cos(Math.toRadians(myTurtle.getOrientation())));
		Double yCoord=myTurtle.getyCoordinate() + (pixels*Math.cos(Math.toRadians(myTurtle.getOrientation())));
		return setTurtleCoordinates(xCoord,yCoord,Math.abs(pixels));

	}

	public Double turnTurtle(Double degrees){
		Double newDegree=myTurtle.getOrientation()+degrees;
		myTurtle.setOrientation(newDegree);
		return degrees;
	}

	public Double setTurtleHome(){
		return placeTurtle(0.0,0.0);
	}
	
	public Double[] getTurtleCoordinates(){
		Double xCoord= (myTurtle.getxCoordinate() - .5*myWidth);
		Double yCoord= (myTurtle.getyCoordinate() - .5*myHeight);
		Double[] coordinateArray={xCoord, yCoord};
		return coordinateArray;
		
	}
	
	//methods relating to moving the Turtle around in the playground
}
