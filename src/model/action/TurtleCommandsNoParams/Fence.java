package model.action.TurtleCommandsNoParams;

import model.turtle.FenceBorder;
import model.turtle.TurtlePlayground;

public class Fence extends TurtleCommands {

	public Fence(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() throws Exception {
		playground.setBorderHandler(new FenceBorder());
		return 3;
	}

}
