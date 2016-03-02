/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class yCor extends TurtleCommands {

	public yCor(TurtlePlayground playground) {
		super(playground);
	}

	@Override
	public double rule() {
		return playground.getTurtle().getyCoordinate();
	}

}
