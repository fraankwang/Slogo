package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Random extends Action {

	double max;
	
	public Random (double max, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.max = max;
		
	}
	
	public Random (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	}
	@Override
	public double rule() {
		return Math.random()*max;
	}

}
