package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

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
