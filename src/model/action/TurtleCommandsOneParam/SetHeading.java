package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class SetHeading extends TurtleCommandsOneParam{

	public SetHeading(List<Double> params, TurtlePlayground playground){
		super(params, playground);
	}


	@Override
	public double rule() {
		Double difference=playground.getTurtle().getOrientation()-value;
		playground.getTurtle().setOrientation(value);
		return difference;
	}
}
