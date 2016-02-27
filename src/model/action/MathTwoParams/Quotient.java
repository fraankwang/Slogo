package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Quotient extends MathTwoParams {
	
	public Quotient(List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return a / b;
		
	}

}
