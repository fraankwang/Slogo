package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Log extends Action {

	double a;

	public Log (double a, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.a = a;

	}
	
	public Log (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0),  playground, variables);
	}

	@Override
	public double rule() {
		return Math.log(a);

	}

}
