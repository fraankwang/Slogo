/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtleCoordinates;
import model.turtle.TurtlePlayground;

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
	}

	/**
	 * The rule() method returns the difference between the turtle's current
	 * position and home.
	 */
	@Override
	public double rule() {
		Double difference =  playground.setTurtleHome();
		System.out.println("old turtle playground coordlist size"+ playground.getTurtleCoordinates().size());

		playground.resetPlayground();
		System.out.println("new turtle playground coordlist size"+ playground.getTurtleCoordinates().size());
		return difference;
	}

}
