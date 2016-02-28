package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class yCor extends TurtleCommands {

	public yCor (TurtlePlayground playground){
		super(playground);
	}
	
	@Override
	public double rule() {
		return playground.getTurtle().getyCoordinate();
	}

}
