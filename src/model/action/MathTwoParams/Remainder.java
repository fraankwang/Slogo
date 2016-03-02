package model.action.MathTwoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Remainder extends MathTwoParams {
	
	public Remainder (List<Double> params){
		super(params);
	}
	
	
	@Override
	public double rule() {
		return a % b;
		
	}
}
