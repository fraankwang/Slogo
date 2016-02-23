package model;

public class Notequal extends Action {

	private double a;
	private double b;
	
	public Notequal (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
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
