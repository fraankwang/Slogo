package model.action.TurtleDisplayCommandsFourParams;

import java.util.List;

import model.Palette;
import model.action.TurtleDisplayCommandsOneParam.TurtleDisplayCommandsParams;
import model.turtle.TurtlePlayground;

public abstract class TurtleDisplayCommandsFourParams extends TurtleDisplayCommandsParams {

	public TurtleDisplayCommandsFourParams(List<Double> newCommands, TurtlePlayground newTurtlePlayground,
			Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
	}


}
