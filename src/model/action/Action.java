package model.action;

import model.TurtlePlayground;

public abstract class Action {
	TurtlePlayground playground;
	public Action(){
		playground = new TurtlePlayground() ;
	}
	public abstract double rule();
	//switched to double for sin/cos/etc

}
