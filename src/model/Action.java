package model;

public abstract class Action {
	TurtlePlayground playground;
	public Action(){
		playground = new TurtlePlayground() ;
	}
	public abstract int rule();

}
