/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class Showing extends TurtleCommands {

	public Showing(TurtlePlayground playground) {
		super(playground);
	}

	@Override
	public double rule() {
		boolean visible = playground.getTurtle().isVisible();
		Double castedVisible = (double) ((visible) ? 1 : 0);
		return castedVisible;
	}

}
