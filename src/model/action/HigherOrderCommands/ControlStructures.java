package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;

public abstract class ControlStructures extends HigherOrderCommands {

	protected List<String> params;

	public ControlStructures(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		this.params = params;

	}

}
