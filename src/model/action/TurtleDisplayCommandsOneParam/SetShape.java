package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetShape extends TurtleDisplayCommandsParams {
	private Double index;

	public SetShape(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
		index = newCommands.get(0);
	}

	@Override
	public double rule() {
		String shape = palette.getShape(index.intValue());
		playground.setCurrentTurtleShape(shape);
		return index;
	}

}
