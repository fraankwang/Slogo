package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Sum extends MathTwoParams {
	

	public Sum (List<Double> params, Variables variables){
		super(params, variables);
	}
	

	@Override
	public double rule() {
		return a + b;
		
	}

}
