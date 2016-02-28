package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class HideTurtle extends TurtleCommands{
	boolean visible;
	
	public HideTurtle (TurtlePlayground playground){
		super(playground);
		this.visible=false;
	}
	
	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
