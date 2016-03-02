/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

/**
 * The xCor class is the user-command which returns the X-Coordinate of the
 * turtle. The xCor class extends from the abstract TurtleCommands class, which
 * contains an instance of the playground, and as a result, the turtle.
 * 
 */
public class xCor extends TurtleCommands {
	public xCor(TurtlePlayground playground) {
		super(playground);

	}

	/**
	 * The rule() method returns the xCoordinate of the turtle within the
	 * playground.
	 */
	@Override
	public double rule() {
		return playground.getTurtle().getxCoordinate();
	}

}
