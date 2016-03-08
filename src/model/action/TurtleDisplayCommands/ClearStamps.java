package model.action.TurtleDisplayCommands;

import model.Configuration;
import model.Palette;
import model.turtle.TurtlePlayground;

public class ClearStamps extends TurtleDisplayCommands {

	public ClearStamps(TurtlePlayground newTurtlePlayground, Configuration newConfiguration, Palette newPalette) {
		super(newTurtlePlayground, newConfiguration, newPalette);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		if (playground.getStampCoordinates().isEmpty()) {
			return 0;
		} else {
			playground.clearStampCoordinate();
			return 1;
		}

	}

}
