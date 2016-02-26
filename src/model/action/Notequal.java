package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Notequal extends Action {

	private double a;
	private double b;
	
	public Notequal (double a, double b, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.a = a;
		this.b = b;
		
	}
	
	public Notequal (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), params.get(1), playground, variables);
	}

	@Override
	public double rule() {
		if (a != b) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
