package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;
import model.action.TurtleCommandsNoParams.TurtleCommands;

public abstract class TurtleCommandsOneParam extends TurtleCommands{
	
	protected double value;
	
	public TurtleCommandsOneParam(List<Double> params, TurtlePlayground playground) {
		super(playground);
		this.value = params.get(0);
	}

}
