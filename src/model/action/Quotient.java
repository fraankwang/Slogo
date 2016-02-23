package model.action;

public class Quotient extends Action {
	
	private double a;
	private double b;
	
	public Quotient (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}
	
	@Override
	public double rule() {
		return a / b;
		
	}

}
