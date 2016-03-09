package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import model.Palette;
import model.turtle.TurtlePlayground;

public class SetPenSize extends TurtleDisplayCommandsParams {
	private Double index;

	public SetPenSize(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
		index = newCommands.get(0);
	}

	@Override
	public double rule() {
		playground.setCurrentPenSize(index);
		return index;
	}

}
