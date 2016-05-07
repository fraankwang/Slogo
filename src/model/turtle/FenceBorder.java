package model.turtle;

public class FenceBorder extends BorderHandler {
	public FenceBorder(){
		super();
	}

	@Override
	public TurtleCoordinates updateCoords(TurtleCoordinates coord, Double myWidth, Double myHeight) {
		if(!inXBounds(coord, myWidth)){
			Double side = coord.getXCoord()/(Math.abs(coord.getXCoord()));
			coord.setXCoord(side*myWidth/2);
		}
		if(!inYBounds(coord, myHeight)){
			Double side = coord.getYCoord()/(Math.abs(coord.getYCoord()));
			coord.setYCoord(side*myHeight/2);
		}
		return coord;

	}

}
