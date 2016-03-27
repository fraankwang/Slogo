package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Palette;
import model.action.TurtleDisplayCommands.TurtleDisplayCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleDisplayCommandsParams extends TurtleDisplayCommands {
	List<Double> commands;

	public TurtleDisplayCommandsParams(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground, newPalette);
		commands = newCommands;
	}

}
