package model.action.TurtleCommandsOneStringParam;

import java.util.List;

import model.action.TurtleCommandsNoParams.TurtleCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsOneStringParam extends TurtleCommands{

	String command;
	
	public TurtleCommandsOneStringParam( List<String> params,TurtlePlayground playground) {
		super(playground);
		command=params.get(0);
		System.out.println(command);
	}

}
