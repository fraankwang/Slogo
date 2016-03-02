package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Forward extends TurtleCommandsOneParam {

	public Forward(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
		// TODO Auto-generated constructor stub
	}


	@Override
	public double rule() {
		playground.moveTurtle(value);
		return value;
	}

}
