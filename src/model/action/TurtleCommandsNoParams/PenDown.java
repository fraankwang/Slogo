package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class PenDown extends TurtleCommands{
	boolean pen;
	
	public PenDown (TurtlePlayground playground){
		super(playground);
		pen=true;
	}
	
	@Override
	public double rule() {
		playground.getTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}
	

}
