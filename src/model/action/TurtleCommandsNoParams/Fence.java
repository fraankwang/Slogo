package model.action.TurtleCommandsNoParams;

import model.WindowBorderBehavior;
import model.turtle.TurtlePlayground;

public class Fence extends TurtleCommands{

	public Fence(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule()  {
		this.playground.setBorder(WindowBorderBehavior.fence);
		return (double) 3;
	}

}
