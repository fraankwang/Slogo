package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class SetXY extends TurtleCommandsTwoParams{

	public SetXY (List<Double> params, TurtlePlayground playground){
		super(params, playground);
	}

	
	@Override
	public double rule() {
		return playground.placeTurtle(value,param2);
	}

}
