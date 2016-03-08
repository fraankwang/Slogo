/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The isPenDown class is the user-command which returns the penDown boolean
 * instance of the turtle. The isPenDown class is the user command to determine
 * whether the pen is down. The isPenDown class extends from the abstract
 * TurtleCommands class, which contains an instance of the playground, and as a
 * result, the turtle.
 * 
 */
public class isPenDown extends TurtleCommands {

	public isPenDown(TurtlePlayground playground) {
		super(playground);
	}

	/**
	 * The rule() method returns the penDown boolean instance of the turtle. The
	 * method returns a boolean for whether the pen is down.
	 */
	@Override
	public double rule() {
		boolean isPenDown = playground.getCurrentTurtle().isPenDown();
		Double castedIsPenDown = (double) ((isPenDown) ? 1 : 0);
		return castedIsPenDown;

	}

}
