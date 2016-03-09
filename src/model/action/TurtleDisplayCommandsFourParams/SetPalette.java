package model.action.TurtleDisplayCommandsFourParams;

import java.util.List;

import javafx.scene.paint.Color;
import model.Palette;
import model.action.TurtleDisplayCommandsOneParam.TurtleDisplayCommandsParams;
import model.turtle.TurtlePlayground;

public class SetPalette extends TurtleDisplayCommandsParams {

	private int red;
	private int green;
	private int blue;
	private int index;

	public SetPalette(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
		index = newCommands.get(0).intValue();
		red = newCommands.get(1).intValue();
		green = newCommands.get(2).intValue();
		blue = newCommands.get(3).intValue();
	}

	@Override
	public double rule() {
		Color color = Color.rgb(red, green, blue);
		palette.addColor(index, color);
		return index;
	}

}
