package model.action.MathTwoParams;

import java.util.List;

import model.Variables;

public class Difference extends MathTwoParams {

	public Difference (List<Double> params){
		super(params);
	}
	

	@Override
	public double rule() {
		return Math.abs(a - b);
		
	}
}
