package model.action.TurtleCommandsTwoStringParams;

import java.util.List;

import model.Palette;
import model.UserCommands;
import model.Variables;
import model.action.HigherOrderCommands.HigherOrderCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsTwoStringParams extends HigherOrderCommands {
	String params;
	String commands;
	TurtlePlayground playground;
	public TurtleCommandsTwoStringParams(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super(language, playground, variables, usercommands, palette);
		this.params = params.get(0);
		commands = params.get(1);
		this.playground = playground;
	}

}
