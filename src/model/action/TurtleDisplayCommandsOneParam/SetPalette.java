package model.action.TurtleDisplayCommandsOneParam;

import java.util.List;

import javafx.scene.paint.Color;
import model.Palette;
import model.turtle.TurtlePlayground;

public class SetPalette extends TurtleDisplayCommandsParams{

	int red;
	int green;
	int blue;
	int index;
	
	public SetPalette(List<Double> newCommands, TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newCommands, newTurtlePlayground, newPalette);
		index=newCommands.get(0).intValue();
		red=newCommands.get(1).intValue();
		green=newCommands.get(2).intValue();
		blue=newCommands.get(3).intValue();
	}

	@Override
	public double rule() {
		Color color=Color.rgb(red, green, blue);
		palette.addColor(index, color);
		return index;
	}

}
