/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class isPenDown extends TurtleCommands {

	public isPenDown(TurtlePlayground playground) {
		super(playground);
	}

	@Override
	public double rule() {
		boolean isPenDown = playground.getTurtle().isPenDown();
		Double castedIsPenDown = (double) ((isPenDown) ? 1 : 0);
		return castedIsPenDown;

	}

}
