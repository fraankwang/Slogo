package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

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
