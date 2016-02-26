package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Atan extends Action {

	double degrees;

	public Atan (double degrees, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.degrees = degrees;

	}
	
	public Atan (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	
	}

	@Override
	public double rule() {
		return Math.toDegrees(Math.atan(Math.toRadians(degrees)));
				
	}

}
