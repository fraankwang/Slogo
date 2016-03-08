package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;

import model.action.Action;

public abstract class TurtleDisplayCommands extends Action {

	protected Palette palette;
	protected Configuration configuration;

	public TurtleDisplayCommands(Configuration newConfiguration, Palette newPalette) {
		super();
		configuration = newConfiguration;
		palette = newPalette;
	}

}
