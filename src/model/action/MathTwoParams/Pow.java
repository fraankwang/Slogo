package model.action.MathTwoParams;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Pow extends MathTwoParams {


	public Pow (List<Double> params){
		super(params);
	}
	
	
	@Override
	public double rule() {
		return Math.pow(a, b);

	}

}
