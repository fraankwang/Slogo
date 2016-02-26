package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Pi extends Action {

	public Pi (TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		
	}
	
	public Pi (List<Double> params, TurtlePlayground playground, Variables variables){
		this(playground, variables);
	}

	@Override
	public double rule() {
		return Math.PI;

	}

}
