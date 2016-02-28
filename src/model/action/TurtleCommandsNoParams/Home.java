package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Home extends TurtleCommands{

	public Home(TurtlePlayground playground) {
		super(playground);
	}
	
	public double rule() {
		return playground.setTurtleHome();
	}
	
	

}
