/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

/**
 * The ShowTurtle class is the user-command which shows the Turtle by setting
 * the showTurtle boolean to true and returns 1. The ShowTurtle class extends
 * from the abstract TurtleCommands class, which contains an instance of the
 * playground, and as a result, the turtle.
 * 
 */
public class ShowTurtle extends TurtleCommands {
	boolean visible;

	public ShowTurtle(TurtlePlayground playground) {
		super(playground);
		this.visible = true;
	}

	/**
	 * The rule() method shows the turtle in the playground, and returns the
	 * boolean equivalent of true, 1. This method sets the Turtle's showTurtle
	 * boolean to true and returns 1.
	 */
	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
