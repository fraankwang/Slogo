package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

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
