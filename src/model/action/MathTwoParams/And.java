package model.action.MathTwoParams;

import java.util.List;

import model.Variables;

public class And extends MathTwoParams {

	
	public And (List<Double> params, Variables variables){
		super(params, variables);
		
		
	}
	
	@Override
	public double rule() {
		if (a != 0 && b != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
