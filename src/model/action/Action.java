package model.action;

import model.TurtlePlayground;
import model.Variables;

public abstract class Action {
	
	TurtlePlayground playground;
	Variables variables;
	
	public Action(double[] params){
		
		playground = new TurtlePlayground() ;
		variables = new Variables();
	}
	public abstract double rule();
	//switched to double for sin/cos/etc

}
