package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class PenColor extends TurtleDisplayCommands {

	public PenColor(TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super(newTurtlePlayground, newConfiguration, newPalette);
	}

	@Override
	public double rule() {
		return palette.getColorIndex(configuration.getPenColor(playground.getCurrentTurtleID()));

	}

}
