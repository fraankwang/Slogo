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
