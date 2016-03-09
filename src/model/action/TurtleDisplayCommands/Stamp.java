package model.action.TurtleDisplayCommands;

import model.Palette;
import model.turtle.TurtlePlayground;

public class Stamp extends TurtleDisplayCommands{

	public Stamp(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground, newPalette);
	}

	@Override
	public double rule() {
		playground.addStampCoordinate();
		return palette.getShapeIndex(playground.getCurrentTurtleShape());
	}

}
