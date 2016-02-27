package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Showing extends TurtleCommands{
	
	
	public Showing (TurtlePlayground playground){
		super(playground);
	}
	@Override
	public double rule() {
		boolean visible=playground.getTurtle().isVisible();
		Double castedVisible = (double) ((visible) ? 1 : 0);
		return castedVisible;
	}

}
