/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

/**
 * The ClearScreen class is the user-command which clears the screen and sets
 * the Turtle at home in its new playground.. The ClearScreen class extends from
 * the abstract TurtleCommands class, which contains an instance of the
 * playground, and as a result, the turtle.
 * 
 */
public class ClearScreen extends TurtleCommands {

	/**
	 * The constructor of this class uses the super-constructor, but sets the
	 * playground to be a new TurtlePlayground in order to clear the screen
	 * 
	 * 
	 */

	public ClearScreen(TurtlePlayground playground) {
		super(playground);
		playground = new TurtlePlayground();
	}

	/**
	 * The rule() method returns the difference between the turtle's current
	 * position and home.
	 */
	@Override
	public double rule() {
		Double difference = (double) playground.getDistance(playground.getTurtle().getxCoordinate(),
				playground.getTurtle().getyCoordinate(), 0.0, 0.0);
		return difference;
	}

}
