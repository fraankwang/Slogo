package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;
import model.turtle.WrapBorder;

public class Wrap extends TurtleCommands {

	public Wrap(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() throws Exception {
		playground.setBorderHandler(new WrapBorder());
		return 1;
	}

}
