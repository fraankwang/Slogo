package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class xCor extends TurtleCommands{

	public xCor ( TurtlePlayground playground){
		super(playground);

	}

	@Override
	public double rule() {
		return playground.getTurtle().getxCoordinate();
	}
	

}
