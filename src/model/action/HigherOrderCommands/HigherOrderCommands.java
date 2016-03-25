// This entire file is part of my masterpiece.
// HUIJIA YU
// I chose this for my masterpiece because it is part of the abstract class hierarchy. 
// It is consistent with the Open/Closed principle (this is closed) and 
// the Liskov Substitution Principle (it can replace an action).

/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import model.CommandParser;
import model.Palette;
import model.UserCommands;
import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public abstract class HigherOrderCommands extends Action {
	protected CommandParser myCommandParser;

	public HigherOrderCommands(String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super();
		myCommandParser = new CommandParser(language, playground, variables, usercommands, palette);

	}

}
