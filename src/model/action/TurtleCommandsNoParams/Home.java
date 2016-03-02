/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

/**
 * The Home class is the user-command which sets the Turtle back home. The Home
 * class extends from the abstract TurtleCommands class, which contains an
 * instance of the playground, and as a result, the turtle.
 * 
 */
public class Home extends TurtleCommands {

	public Home(TurtlePlayground playground) {
		super(playground);
	}

	/**
	 * The rule() method sets the Turtle back to home. Sets x and y coordinates
	 * to (0,0) This method returns the difference from the turtle's current
	 * position and home.
	 */
	public double rule() {
		return playground.setTurtleHome();
	}

}
