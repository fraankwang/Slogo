package model.action;

import java.util.List;

public class And extends Action {

	private double a;
	private double b;
	
	public And (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}
	public And (List<Double>c){
		this(c.get(0), c.get(1));
	}
	
	@Override
	public double rule() {
		if (a != 0 && b != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
