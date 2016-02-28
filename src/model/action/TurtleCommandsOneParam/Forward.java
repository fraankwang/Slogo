package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Forward extends TurtleCommandsOneParam {

	public Forward(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
		// TODO Auto-generated constructor stub
	}


	@Override
	public double rule() {
		playground.moveTurtle(value);
		System.out.println(playground.getTurtleCoordinates()[0]+" , "+ playground.getTurtleCoordinates()[1]);
		return value;
	}

}
