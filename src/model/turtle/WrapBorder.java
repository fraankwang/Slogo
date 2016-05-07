package model.turtle;

public class WrapBorder extends BorderHandler {
	public WrapBorder(){
		super();
	}

	@Override
	public TurtleCoordinates updateCoords(TurtleCoordinates coord, Double myWidth, Double myHeight) {
		if(!inXBounds(coord, myWidth)){
			Double check = coord.getXCoord()+myWidth/2;
			check = check%myWidth;
			coord.setXCoord(check-myWidth/2);
		}
		if(!inYBounds(coord, myHeight)){
			Double check = coord.getYCoord()+myHeight/2;
			check = check%myHeight;
			coord.setYCoord(check-myHeight/2);
		}
		return coord;

	}

}
