package model.action.HigherOrderCommands;

import java.util.List;
import java.util.Map;

import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

public abstract class ControlStructures extends HigherOrderCommands {

	protected List<String> params;

	public ControlStructures(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		this.params = params;


	}

}
