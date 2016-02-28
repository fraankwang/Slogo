package model.action.HigherOrderCommands;

import java.util.List;

import model.CommandParser;
import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public abstract class HigherOrderCommands extends Action {
protected CommandParser myCommandParser;

	public HigherOrderCommands(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super();
		myCommandParser = new CommandParser(language, playground, variables, usercommands);
	}


}
