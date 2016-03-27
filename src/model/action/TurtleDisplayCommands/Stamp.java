package model.action.TurtleDisplayCommands;

import model.Palette;
import model.turtle.TurtlePlayground;
/* This entire file is part of my masterpiece.  
 * This class is part of my masterpiece because it demonstrates an action which extends from the TurtleDisplayCommands class.
 * The rule() method for this action requires access to the playground object.
 * After refactoring, I believe that this action demonstrates why my choice to extend TurtleDisplayCommands from TurtleCommands.
 * The instance of playground accessed here was initialized through using the TurtleCommand super constructor. 
 * Previously, that instance would have been initialized by the TurtleDisplayCommand constructor which is a reptitition in logic.
 * Srikar Pyda
*/
public class Stamp extends TurtleDisplayCommands{

	public Stamp(TurtlePlayground newTurtlePlayground, Palette newPalette) {
		super(newTurtlePlayground, newPalette);
	}

	@Override
	public double rule() {
		playground.addStampCoordinate();
		return palette.getShapeIndex(playground.getCurrentTurtleShape());
	}

}
