package model.action.TurtleCommandsTwoStringParams;

import java.util.List;

import model.UserCommands;
import model.Variables;
import model.action.HigherOrderCommands.HigherOrderCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsTwoStringParams extends HigherOrderCommands {
	String params;
	String commands;
	TurtlePlayground playground;
	public TurtleCommandsTwoStringParams(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		this.params = params.get(0);
		commands = params.get(1);
		this.playground = playground;
	}

}
