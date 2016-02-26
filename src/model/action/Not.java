package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Not extends Action {

	private double a;
	
	public Not (double a, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.a = a;
		
	}
	
	
	public Not (List<Double> params, TurtlePlayground playground, Variables variables){
		this(params.get(0), playground, variables);
	}

	@Override
	public double rule() {
		if (a == 0 ) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
