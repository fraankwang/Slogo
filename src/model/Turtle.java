/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

public class Turtle {
	private int xCoordinate;
	private int yCoordinate;
	private boolean penDown;
	//other info like pen color and turtle image can be added later
	
	
	public void setPenDown (boolean down) {
		penDown = down;
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
