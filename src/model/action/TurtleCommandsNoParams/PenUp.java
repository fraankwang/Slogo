/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class PenUp extends TurtleCommands {
	boolean pen;

	public PenUp(TurtlePlayground playground) {
		super(playground);
		pen = false;
	}

	@Override
	public double rule() {
		playground.getTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}
}
