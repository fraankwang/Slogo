package model.action.MathOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Random extends MathOneParam {

	
	public Random (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return Math.random()*a;
	}

}
