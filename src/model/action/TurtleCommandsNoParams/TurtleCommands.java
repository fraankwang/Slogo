package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommands extends Action {

	protected TurtlePlayground playground;

	public TurtleCommands(TurtlePlayground playground) {
		super();
		this.playground=playground;
	}

}
