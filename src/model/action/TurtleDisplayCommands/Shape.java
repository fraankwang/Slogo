package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class Shape extends TurtleDisplayCommands {

	public Shape(TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super(newTurtlePlayground, newConfiguration, newPalette);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		return palette.getShapeIndex(configuration.getTurtleShape(playground.getCurrentTurtleID()));

	}

}
