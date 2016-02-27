package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Greater extends MathTwoParams {

	public Greater (List<Double> params, Variables variables){
		super(params, variables);
	}
	

	@Override
	public double rule() {
		if (a > b) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
