package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class ID extends TurtleCommands{

	public ID(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		return playground.getCurrentTurtleID();
	}
	
	

}
