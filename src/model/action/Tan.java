package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Tan extends Action {

	Double degrees;

	public Tan (Double degrees, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.degrees = degrees;

	}
	
	public Tan (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	}

	@Override
	public double rule() {
		if (degrees % 90 == 0) {
			return 0;
		}
		else {
			return Math.tan(Math.toRadians(degrees));
		}
				
	}

}
