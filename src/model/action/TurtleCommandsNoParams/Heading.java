package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.turtle.TurtlePlayground;

public class Heading extends TurtleCommands {

	
	public Heading (TurtlePlayground playground){
		super(playground);
	}
	
	@Override
	public double rule() {
		return playground.getTurtle().getOrientation();
	}

}
