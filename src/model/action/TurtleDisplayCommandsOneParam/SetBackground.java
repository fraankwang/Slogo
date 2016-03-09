package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import javafx.scene.paint.Color;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetBackground extends TurtleDisplayCommandsParams {
	private Double index;

	public SetBackground(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
		index = newCommands.get(0);
	}

	@Override
	public double rule() {
		Color backgroundColor = palette.getColor(index.intValue());
		playground.setBackgroundColor(backgroundColor);
		return index;
	}

}
