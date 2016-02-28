package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

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
