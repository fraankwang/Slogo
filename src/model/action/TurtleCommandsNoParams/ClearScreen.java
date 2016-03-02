/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class ClearScreen extends TurtleCommands {

	public ClearScreen(TurtlePlayground playground) {
		super(playground);
		playground = new TurtlePlayground();
	}

	@Override
	public double rule() {
		Double difference = (double) playground.getDistance(playground.getTurtle().getxCoordinate(),
				playground.getTurtle().getyCoordinate(), 0.0, 0.0);
		return difference;
	}

}
