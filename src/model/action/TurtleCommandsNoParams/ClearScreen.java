package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.turtle.TurtlePlayground;

public class ClearScreen extends TurtleCommands {
	
	public ClearScreen(TurtlePlayground playground){
		super(playground);
		playground=new TurtlePlayground();
	}
	
	@Override
	public double rule() {
		Double difference=(double) playground.getDistance(playground.getTurtle().getxCoordinate(), playground.getTurtle().getyCoordinate(), 0.0, 0.0);
		return difference;
	}

}
