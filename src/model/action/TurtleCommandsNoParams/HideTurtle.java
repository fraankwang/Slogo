/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The HideTurtle class is the user-command which hides the turtle on the
 * playground. The HideTurtle class is the Action which contains the rules()
 * method which sets the showTurtle boolean instance The HideTurtle class
 * extends from the abstract TurtleCommands class, which contains an instance of
 * the playground, and as a result, the turtle.
 * 
 */
public class HideTurtle extends TurtleCommands {
	boolean visible;

	public HideTurtle(TurtlePlayground playground) {
		super(playground);
		this.visible = false;
	}

	/**
	 * The rule() method sets the showTurtle boolean instance variable of the
	 * turtle to false. This method hides the turtle on the playground and
	 * returns 0.
	 * 
	 */

	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
