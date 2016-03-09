package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import javafx.scene.paint.Color;
import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetPenColor extends TurtleDisplayCommandsParams {
	Double index;

	public SetPenColor(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Configuration newConfiguration,
			Palette newPalette) {
		super(newCommands, newTurtlePlayground, newConfiguration, newPalette);
		index = newCommands.get(0);
	}

	@Override
	public double rule() {
		Color color=palette.getColor(index.intValue());
		configuration.addPenColor(playground.getCurrentTurtleID(), color);
		return index;
	}

}
