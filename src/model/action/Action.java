package model.action;

import model.TurtlePlayground;
import model.Variables;

public abstract class Action {
		Variables variables;
	
	public Action(Variables newVariables){
		variables = newVariables;
	}
	
	public abstract double rule();
	
}
