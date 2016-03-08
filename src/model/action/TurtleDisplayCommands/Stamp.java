package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class Stamp extends TurtleDisplayCommands{

	public Stamp(TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super(newTurtlePlayground, newConfiguration, newPalette);
	}

	@Override
	public double rule() {
		playground.addStampCoordinate();
		return palette.getShapeIndex(configuration.getTurtleShape(playground.getCurrentTurtleID()));
	}

}
