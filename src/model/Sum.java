package model;

public class Sum extends Action {
	
private double a;
private double b;

	public Sum (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}

	@Override
	public double rule() {
		return a + b;
		
	}

}
