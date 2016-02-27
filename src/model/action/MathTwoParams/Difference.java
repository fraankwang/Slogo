package model.action.MathTwoParams;

import java.util.List;

import model.Variables;

public class Difference extends MathTwoParams {


	public Difference (List<Double> params, Variables variables){
		super(params, variables);
	}
	

	@Override
	public double rule() {
		return Math.abs(a - b);
		
	}
}
