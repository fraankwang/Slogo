package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public class Make extends ControlStructures {

	private String var;
	private double exp;

	public Make (String var, double exp, List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		this.var = var;
		this.exp = exp;

	}

	@Override
	public double rule() {
		myCommandParser.getVariableList().addVariable(var, exp);
		return exp;
		
	}

}
