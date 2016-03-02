package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.turtle.TurtlePlayground;

public class isPenDown extends TurtleCommands{

	
	public isPenDown (TurtlePlayground playground){
		super(playground);
	}

	@Override
	public double rule() {
		boolean isPenDown=playground.getTurtle().isPenDown();
		Double castedIsPenDown = (double) ((isPenDown) ? 1 : 0);
		return castedIsPenDown;
	
	}
	
	
}
