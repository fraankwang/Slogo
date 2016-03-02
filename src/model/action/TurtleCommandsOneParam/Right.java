package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Right extends TurtleCommandsOneParam{


	public Right (List<Double> params, TurtlePlayground playground){
		super(params, playground);
	}

	@Override
	public double rule() {
		return playground.turnTurtle(value);
	}
}
