package model.action.TurtleDisplayCommands;

import model.Palette;

import model.action.Action;
import model.turtle.TurtlePlayground;

public abstract class TurtleDisplayCommands extends Action {

	protected Palette palette;
	protected TurtlePlayground playground;

	public TurtleDisplayCommands(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super();
		playground = newTurtlePlayground;
		palette = newPalette;
	}

}
