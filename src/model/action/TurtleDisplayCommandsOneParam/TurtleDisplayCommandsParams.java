package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Palette;
import model.action.TurtleDisplayCommands.TurtleDisplayCommands;

public abstract class TurtleDisplayCommandsParams extends TurtleDisplayCommands{
	List<Double> commands;
	public TurtleDisplayCommandsParams(Palette newPalette, List<Double> newCommands) {
		super(newPalette);
		commands=newCommands;
		// TODO Auto-generated constructor stub
	}


}
