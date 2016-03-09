package model.action.TurtleCommandsOneStringParam;

import java.util.List;

import model.action.TurtleCommandsNoParams.TurtleCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsOneStringParam extends TurtleCommands{

	protected String turtles;
	
	public TurtleCommandsOneStringParam( List<String> params,TurtlePlayground playground) {
		super(playground);
		turtles=params.get(0);
		System.out.println(turtles);
	}

}
