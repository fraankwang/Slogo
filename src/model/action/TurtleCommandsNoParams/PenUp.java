/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The PenUp class is the user-command which sets the pen up by setting the
 * penDown boolean instance variable in Turtle to false. The PenUp class extends
 * from the abstract TurtleCommands class, which contains an instance of the
 * playground, and as a result, the turtle.
 * 
 */
public class PenUp extends TurtleCommands {
	boolean pen;

	public PenUp(TurtlePlayground playground) {
		super(playground);
		pen = false;
	}

	/**
	 * The rule() method sets the pen up by setting the turtle's penDown boolean
	 * instance variable to false;
	 */

	@Override
	public double rule() {
		playground.getCurrentTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}
}
