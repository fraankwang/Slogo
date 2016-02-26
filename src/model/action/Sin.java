package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Sin extends Action {

	double degrees;

	public Sin (double degrees, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.degrees = degrees;

	}
	
	public Sin (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(degrees));
				
	}

}
