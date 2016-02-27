package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Tan extends MathOneParam {


	public Tan (List<Double> params, Variables variables){
		super(params, variables);
	}

	@Override
	public double rule() {
		if (a % 90 == 0) {
			return 0;
		}
		else {
			return Math.tan(Math.toRadians(a));
		}
				
	}

}
