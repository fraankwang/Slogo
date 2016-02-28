package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Heading extends TurtleCommands {

	
	public Heading (TurtlePlayground playground){
		super(playground);
	}
	
	@Override
	public double rule() {
		return playground.getTurtle().getOrientation();
	}

}
