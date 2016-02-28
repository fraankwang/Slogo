package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

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
