/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package src.model;

public class Turtle {
	private int xCoordinate;
	private int yCoordinate;
	private boolean penDown;
	private boolean showTurtle;
	private float Orientation;
	//other info like pen color and turtle image can be added later
	public Turtle(){
		penDown=false;
		showTurtle=false;
		Orientation=90;
	}
	
	public void setPenDown (boolean down) {
		this.penDown = down;
	}
	
	public void setShowTurtle(boolean show){
		this.showTurtle=show;
	}
	
	public void setOrientation(float orientation){
		this.Orientation=orientation;
	}
	
	public float getOrientation(){
		return this.Orientation;
	}
	public int getxCoordinate () {
		return xCoordinate;
	}

	public void setxCoordinate (int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate () {
		return yCoordinate;
	}

	public void setyCoordinate (int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

}
