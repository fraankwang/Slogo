package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class PenUp extends TurtleCommands{
	boolean pen;


	public PenUp (TurtlePlayground playground){
		super(playground);
		pen=false;
	}
	
	@Override
	public double rule() {
		playground.getTurtle().setPenDown(pen);
		return (double) ((pen) ? 1 : 0);
	}
}
