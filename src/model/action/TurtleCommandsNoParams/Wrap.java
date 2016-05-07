package model.action.TurtleCommandsNoParams;

import model.WindowBorderBehavior;
import model.turtle.TurtlePlayground;

public class Wrap extends TurtleCommands{

	public Wrap(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule()  {
		this.playground.setBorder(WindowBorderBehavior.wrap);
		return (double) 1;
	}

}
