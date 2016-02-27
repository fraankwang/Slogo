package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Or extends MathTwoParams {

	public Or (List<Double> params){
		super(params);
	}
	
	
	@Override
	public double rule() {
		if (a != 0 || b != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
