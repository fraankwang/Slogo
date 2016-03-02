/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

/**
 * The TurtleCommands class is an abstract class which extends from the super-class action.
 * The TurtleCommand class has an instance of a TurtlePlayground, because all of the Turtle commands require access to either the playground or the turtle.
 * All turtle command classes which do not require any parameters extend from this abstract class.
 */
import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public abstract class TurtleCommands extends Action {

	protected TurtlePlayground playground;

	/**
	 * This constructor initializes a TurtleCommands object by initializing an
	 * instance of a TurtlePlayground in addition to using the
	 * super-constructor.
	 */
	public TurtleCommands(TurtlePlayground playground) {
		super();
		this.playground = playground;
	}

}
