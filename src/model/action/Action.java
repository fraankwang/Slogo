package model.action;

import model.TurtlePlayground;
import model.Variables;

public abstract class Action {
	
	TurtlePlayground playground;
	Variables variables;
	
	public Action(){
		playground = new TurtlePlayground() ;
		variables = new Variables();
	}
	public abstract double rule();
	//switched to double for sin/cos/etc
	
	public void setTurtlePlayground(TurtlePlayground playground){
		this.playground=playground;
	}
}
