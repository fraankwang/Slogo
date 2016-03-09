package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import javafx.scene.shape.Shape;
import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetShape extends TurtleDisplayCommandsParams{
	Double index;
	public SetShape(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Configuration newConfiguration,
			Palette newPalette) {
		super(newCommands, newTurtlePlayground, newConfiguration, newPalette);
		index=newCommands.get(0);
	}

	@Override
	public double rule() {
		String shape=configuration.getTurtleShape(index.intValue());
		configuration.addTurtleShape(playground.getCurrentTurtleID(), shape);
		return index;
	}

}
