package model.action.TurtleDisplayCommands;

import model.Palette;
import model.turtle.TurtlePlayground;

public class Shape extends TurtleDisplayCommands {

	public Shape(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground, newPalette);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		return palette.getShapeIndex(playground.getCurrentTurtleShape());

	}

}
