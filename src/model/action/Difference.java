package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Difference extends Action {
	
private double a;
private double b;

	public Difference (double a, double b, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.a = a;
		this.b = b;
		
	}
	
	public Difference (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), params.get(1), playground, variables);
	}

	@Override
	public double rule() {
		return Math.abs(a - b);
		
	}
}
