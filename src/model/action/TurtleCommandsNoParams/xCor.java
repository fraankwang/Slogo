/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class xCor extends TurtleCommands {

	public xCor(TurtlePlayground playground) {
		super(playground);

	}

	@Override
	public double rule() {
		return playground.getTurtle().getxCoordinate();
	}

}
