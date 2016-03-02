/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class ShowTurtle extends TurtleCommands {
	boolean visible;

	public ShowTurtle(TurtlePlayground playground) {
		super(playground);
		this.visible = true;
	}

	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
