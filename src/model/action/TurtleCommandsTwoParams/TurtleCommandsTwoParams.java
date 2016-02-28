package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.TurtleCommandsOneParam.TurtleCommandsOneParam;

public abstract class TurtleCommandsTwoParams extends TurtleCommandsOneParam{
	protected double param2;
	
	public TurtleCommandsTwoParams(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
		this.param2=params.get(1);
	}
}
