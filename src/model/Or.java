package model;

public class Or extends Action {

	private double a;
	private double b;
	
	public Or (double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}

	@Override
	public double rule() {
		if (a != 0 || b != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
