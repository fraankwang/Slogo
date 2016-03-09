package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;

import model.action.Action;
import model.turtle.TurtlePlayground;

public abstract class TurtleDisplayCommands extends Action {

	protected Palette palette;
	protected Configuration configuration;
	protected TurtlePlayground playground;

	public TurtleDisplayCommands(TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super();
		playground = newTurtlePlayground;
		configuration = newConfiguration;
		palette = newPalette;
	}

}
