package model.action.TurtleDisplayCommands;

import model.Palette;

import model.action.Action;
import model.action.TurtleCommandsNoParams.TurtleCommands;
import model.turtle.TurtlePlayground;

/* This entire file is part of my masterpiece.  
 * 
 * The TurtleDisplayCommands class is an abstract class which extends from the
 * super-class Action. It has an instance of a TurtlePlayground and a Palette,
 * because all of the Turtle Display commands require access to either the
 * playground or the turtle and the maps contained within the Palette. All
 * actions which do not require any parameters and require access to a
 * TurtlePlayground and Palette extend from this abstract class. 
 * 
 * I refactored this class to extend from TurleCommands instead of Actions.
 * This class ought to extend from the TurtleCommand class instead of the Action class
 * because we repeat the initialization of the TurtlePlayground. A better design
 * would have extended from the TurtleCommand class because it would have better
 * utilized the logic of the hierarchy and would have repeated less code. My refactoring avoid repeating the
 * logic in multiple places. 
 * This class was refactored extensively--the action hierarchy did not extend as deeply as it did before; this class did not exist before refactoring. 
 * This was chosen for my masterpiece because I believe it demonstrates an understanding of the inheritance hierarchy we have covered in depth in class. However, because it was previously extending from Action instead of TurtleCommands, there was a repitition in logic that my refactoring corrected.
 * This class utilizes the super-constructor for initializing the playground as opposed to having the class' constructor initialize a new playground which avoids a repitition in logic.
 * I believe that this class represents a good example of a an enclosed design, because it does not have any dependencies with other classes outside of the hierarchy, especially on the front-end.
 * This code adheres to the open-closed principle because all members are private.
 * Furthermore, I refactored this code significantly in order to minimize repetitiveness. 
 * Overall, the class is short and does not include any extraneous instance variables or methods. In addition, all method and variable names are named relative to their intent within the code. Furthermore, methods are short, and designed to do their own unique purposes.
 * Srikar Pyda
*/
public abstract class TurtleDisplayCommands extends TurtleCommands {

	protected Palette palette;

	public TurtleDisplayCommands(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground);
		palette = newPalette;
	}

}
