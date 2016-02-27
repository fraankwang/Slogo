package model.action.HigherOrderCommands;

import java.util.List;

import model.CommandParser;
import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;

public class To extends HigherOrderCommands {

	String commandname;
	String parametervariables;
	String commands;
	public To(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		commandname = params.get(0);
		parametervariables = params.get(1);
		commands = params.get(2);
	}

	@Override
	public double rule() {
		 usercommands.addCommand(commandname, commands);
	}

}
