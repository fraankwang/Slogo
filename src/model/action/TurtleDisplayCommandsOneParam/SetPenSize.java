package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetPenSize extends TurtleDisplayCommandsParams{
	Double index;
	public SetPenSize(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Configuration newConfiguration,
			Palette newPalette) {
		super(newCommands, newTurtlePlayground, newConfiguration, newPalette);
		index=newCommands.get(0);
	}

	@Override
	public double rule() {
		configuration.addPenSize(playground.getCurrentTurtleID(), index);
		return index;
	}

}
