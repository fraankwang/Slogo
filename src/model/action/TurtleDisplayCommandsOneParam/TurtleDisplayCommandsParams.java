package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Configuration;
import model.Palette;
import model.action.TurtleDisplayCommands.TurtleDisplayCommands;

public abstract class TurtleDisplayCommandsParams extends TurtleDisplayCommands {
	List<Double> commands;

	public TurtleDisplayCommandsParams(Configuration newConfiguration, Palette newPalette, List<Double> newCommands) {
		super(newConfiguration, newPalette);
		commands = newCommands;
		// TODO Auto-generated constructor stub
	}

}
