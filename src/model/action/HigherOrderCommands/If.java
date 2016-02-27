package model.action.HigherOrderCommands;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class If extends Action{
	private String expr;
	private String commands;
	private CommandParser 

	public If(String expr, String commands, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.expr = expr;
		this.commands = commands;
	
	}

	@Override
	public double rule() {
		if(parse(expr)) != 0){
			return parse commands
		}
		else {
			return 0;
		}
		
	}
}
