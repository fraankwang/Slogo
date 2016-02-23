package model;

public class Remainder extends Action {
	
	private double a;
	private double b;

	public Remainder (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}
	
	@Override
	public double rule() {
		return a % b;
		
	}
}
