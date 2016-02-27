package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class xCor extends TurtleCommands{

	public xCor ( TurtlePlayground playground){
		super(playground);

	}

	@Override
	public double rule() {
		return playground.getTurtle().getxCoordinate();
	}
	

}
