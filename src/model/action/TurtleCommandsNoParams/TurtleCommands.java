package model.action.TurtleCommandsNoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public abstract class TurtleCommands extends Action {

	protected TurtlePlayground playground;

	public TurtleCommands(TurtlePlayground playground) {
		super();
		this.playground=playground;
	}

}
