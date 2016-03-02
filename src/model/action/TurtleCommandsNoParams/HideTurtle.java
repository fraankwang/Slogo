/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class HideTurtle extends TurtleCommands {
	boolean visible;

	public HideTurtle(TurtlePlayground playground) {
		super(playground);
		this.visible = false;
	}

	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
