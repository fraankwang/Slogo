package model.action.MathTwoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Quotient extends MathTwoParams {
	
	public Quotient(List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return a / b;
		
	}

}
