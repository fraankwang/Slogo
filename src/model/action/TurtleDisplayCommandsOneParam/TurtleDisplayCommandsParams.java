package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Configuration;
import model.Palette;
import model.action.TurtleDisplayCommands.TurtleDisplayCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleDisplayCommandsParams extends TurtleDisplayCommands {
	List<Double> commands;

	public TurtleDisplayCommandsParams(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super(newTurtlePlayground, newConfiguration, newPalette);
		commands = newCommands;
		// TODO Auto-generated constructor stub
	}

}
