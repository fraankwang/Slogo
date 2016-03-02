package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.action.TurtleCommandsNoParams.TurtleCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsOneParam extends TurtleCommands{
	
	protected double value;
	
	public TurtleCommandsOneParam(List<Double> params, TurtlePlayground playground) {
		super(playground);
		this.value = params.get(0);
	}

}
