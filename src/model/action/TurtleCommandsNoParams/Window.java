package model.action.TurtleCommandsNoParams;

import model.WindowBorderBehavior;
import model.turtle.TurtlePlayground;

public class Window extends TurtleCommands {

	public Window(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		this.playground.setBorder(WindowBorderBehavior.window);
		return (double) 2;
	}

}
