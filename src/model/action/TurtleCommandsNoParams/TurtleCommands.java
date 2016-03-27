/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.action.Action;
import model.turtle.TurtlePlayground;

/* This entire file is part of my masterpiece.  
 * This super-class is part of my masterpiece to show the logic in the action hierarchy. I did not include the Action() abstract class in it because it is the root of all actions.
 * Our original design for the action hierarchy had only the Action super-class extending into the multitude of action. We implemented the Action class with instances of all the different custom objects we created in the model package. This approach was inefficient because all of the actions had acecss to objects which they never even accessed, which created a redundant reptititon in logic.
 * However, I chose to include this class because it has an instance of the TurtlePlayground.
 * Previously, the TurtleDisplayCommands class did not extend from here, which showed a lack of complete utilization of the hierarchy.
 * Through refactoring the code to extend from here, I believe I show an in depth understanding of manipulating the inheritance hierarchy to have access to all the data structures necessary without repeating the logic of my code.
 * Furthermore, I believe that this code is a good example of my masterpiece because the action hierarchy was refactored over and over again in order to minimize the amount of logic repeated.
 * Originally, we thought there would be only one action super-class which had all of the different model variables. However, after refactoring, we decided to implement the extensive hierarchy with a variety of abstract sub-classes to the action super class.
 * I hope that this masterpiece demonstrates that we have learned a proper utilization of the inheritance hierarchy.
 * Srikar Pyda
*/

/**
 * The TurtleCommands class is an abstract class which extends from the
 * super-class action. The TurtleCommand class has an instance of a
 * TurtlePlayground, because all of the Turtle commands require access to either
 * the playground or the turtle. All turtle command classes which do not require
 * any parameters extend from this abstract class.
 */
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
