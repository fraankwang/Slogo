/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The Heading class is the user-command which returns the Heading/Orientation
 * of the turtle. The Heading class extends from the abstract TurtleCommands
 * class, which contains an instance of the playground, and as a result, the
 * turtle.
 * 
 */
public class Heading extends TurtleCommands {

	public Heading(TurtlePlayground playground) {
		super(playground);
	}

	/**
	 * The rule() method returns the current Heading/Orientation of the turtle.
	 */
	@Override
	public double rule() {
		return playground.getCurrentTurtle().getOrientation();
	}

}
