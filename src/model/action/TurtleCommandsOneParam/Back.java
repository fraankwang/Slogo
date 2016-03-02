package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Back extends TurtleCommandsOneParam{

	public Back(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
	}
	

	@Override
	public double rule() {
		Double backPixels=-1*value;
		playground.moveTurtle(backPixels);
		return backPixels;
	}

}
