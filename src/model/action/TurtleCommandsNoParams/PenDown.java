/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The PenDown class is the user-command which sets the pen up by setting the
 * penDown boolean instance variable in Turtle to true. The PenDown class
 * extends from the abstract TurtleCommands class, which contains an instance of
 * the playground, and as a result, the turtle.
 * 
 */
public class PenDown extends TurtleCommands {
	boolean pen;

	public PenDown(TurtlePlayground playground) {
		super(playground);
		pen = true;
	}

	/**
	 * The rule() method sets the pen down by setting the turtle's penDown
	 * boolean instance variable to true;
	 */
	@Override
	public double rule() {
		playground.getTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}

}
