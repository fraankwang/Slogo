package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;
import model.turtle.WindowBorder;

public class Window extends TurtleCommands {

	public Window(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() throws Exception {
		playground.setBorderHandler(new WindowBorder());
		return 2;
	}

}
