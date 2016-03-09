package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import javafx.scene.paint.Color;
import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetBackground extends TurtleDisplayCommandsParams {
	Double index;
	public SetBackground(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Configuration newConfiguration,
			Palette newPalette) {
		super(newCommands, newTurtlePlayground, newConfiguration, newPalette);
		index=newCommands.get(0);
	}

	@Override
	public double rule() {
		Color backgroundColor=palette.getColor(index.intValue());
		configuration.setBackgroundColor(backgroundColor);
		return index;
	}

}
