package model.action.MathTwoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Greater extends MathTwoParams {

	public Greater (List<Double> params){
		super(params);
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
