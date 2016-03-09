package model.action.TurtleDisplayCommands;

import model.Palette;
import model.turtle.TurtlePlayground;

public class ClearStamps extends TurtleDisplayCommands {

	public ClearStamps(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground, newPalette);
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
