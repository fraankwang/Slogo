package model.action;

import java.util.List;

public class Sum extends Action {
	
private double a;
private double b;

	public Sum (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}
	public Sum (List<Double>c){
		this(c.get(0), c.get(1));
	}

	@Override
	public double rule() {
		return a + b;
		
	}

}
