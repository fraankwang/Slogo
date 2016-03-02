package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

/**
 * The yCor class is the user-command which returns the Y-Coordinate of the
 * turtle. The yCor class extends from the abstract TurtleCommands class, which
 * contains an instance of the playground, and as a result, the turtle.
 * 
 */
public class yCor extends TurtleCommands {

	public yCor(TurtlePlayground playground) {
		super(playground);
	}

	/**
	 * The rule() method returns the yCoordinate of the turtle within the
	 * playground..
	 */
	@Override
	public double rule() {
		return playground.getTurtle().getyCoordinate();
	}

}
