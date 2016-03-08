/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The Showing class is the user-command which returns whether the Turtle is
 * showing or not by returning the showTurtle boolean, casted as a double. The
 * Showing class extends from the abstract TurtleCommands class, which contains
 * an instance of the playground, and as a result, the turtle.
 * 
 */
public class Showing extends TurtleCommands {

	public Showing(TurtlePlayground playground) {
		super(playground);
	}

	/**
	 * The rule() method returns the showTurtle boolean for the Turtle within
	 * the playground. This method returns the Double equivalent of the
	 * showTurtle boolean.
	 */

	@Override
	public double rule() {
		boolean visible = playground.getCurrentTurtle().isVisible();
		Double castedVisible = (double) ((visible) ? 1 : 0);
		return castedVisible;
	}

}
