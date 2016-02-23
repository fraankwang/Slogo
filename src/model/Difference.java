package model;

public class Difference extends Action {
	
private double a;
private double b;

	public Difference (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}

	@Override
	public double rule() {
		return Math.abs(a - b);
		
	}
}
