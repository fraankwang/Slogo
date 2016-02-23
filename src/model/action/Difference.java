package model.action;

public class Difference extends Action {
	
private double a;
private double b;

	public Difference (double[] params) {
		super(params);
		a = params[0];
		b = params[1];

	}

	@Override
	public double rule() {
		return Math.abs(a - b);
		
	}
}
