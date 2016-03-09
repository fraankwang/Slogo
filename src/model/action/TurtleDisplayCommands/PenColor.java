package model.action.TurtleDisplayCommands;

import model.Palette;
import model.turtle.TurtlePlayground;

public class PenColor extends TurtleDisplayCommands {

	public PenColor(TurtlePlayground newTurtlePlayground,  Palette newPalette) {
		super(newTurtlePlayground, newPalette);
	}

	@Override
	public double rule() {
		return palette.getColorIndex(playground.getCurrentPenColor());

	}

}
