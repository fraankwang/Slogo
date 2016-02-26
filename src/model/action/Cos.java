package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Cos extends Action {

	double degrees;

	public Cos (double degrees, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.degrees = degrees;
	}

	public Cos (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	}
	@Override
	public double rule() {
		return Math.cos(Math.toRadians(degrees));
				
	}

}
