package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class ShowTurtle extends TurtleCommands{
	boolean visible;

	public ShowTurtle (TurtlePlayground playground){
		super(playground);
		this.visible=true;
	}
	
	@Override
	public double rule() {
		playground.getTurtle().setShowTurtle(visible);
		return (double) ((visible) ? 1 : 0);
	}

}
