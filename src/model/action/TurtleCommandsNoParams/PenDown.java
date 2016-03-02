/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class PenDown extends TurtleCommands {
	boolean pen;

	public PenDown(TurtlePlayground playground) {
		super(playground);
		pen = true;
	}

	@Override
	public double rule() {
		playground.getTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}

}
