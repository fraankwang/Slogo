package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.turtle.TurtlePlayground;

public class Home extends TurtleCommands{

	public Home(TurtlePlayground playground) {
		super(playground);
	}
	
	public double rule() {
		return playground.setTurtleHome();
	}
	
	

}
