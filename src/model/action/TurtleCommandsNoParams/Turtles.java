package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class Turtles extends TurtleCommands {

	public Turtles(TurtlePlayground playground) {
		super(playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		return playground.getTurtleList().size();
	}

}
