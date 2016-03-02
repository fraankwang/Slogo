package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class SetXY extends TurtleCommandsTwoParams{

	public SetXY (List<Double> params, TurtlePlayground playground){
		super(params, playground);
	}

	
	@Override
	public double rule() {
		return playground.placeTurtle(value,param2);
	}

}
