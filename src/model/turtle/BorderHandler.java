package model.turtle;

public abstract class BorderHandler {
	protected double myWidth;
	protected double myHeight;
	public BorderHandler(){

		
	}
	/**
	 * The inBounds() method takes in a coordinate pair and returns a boolean
	 * indicating whether the coordinate pair is within the boundaries of the
	 * playground.
	 *
	 */

	public boolean inXBounds(TurtleCoordinates coordinate, Double myWidth) {
		return !(coordinate.getXCoord() < -myWidth / 2 || coordinate.getXCoord() > myWidth / 2
				);

	}
	public boolean inYBounds(TurtleCoordinates coordinate, Double myHeight){
		return !(coordinate.getYCoord() < -myHeight / 2 || coordinate.getYCoord() > myHeight / 2);
		
	}
	
	public abstract TurtleCoordinates updateCoords(TurtleCoordinates coord, Double myWidth, Double myHeight);
}
