package model.action;

public class Sum extends Action {
	
private double a;
private double b;

	public Sum (double[] params) {
		super(params);
		a = params[0];
		b = params[1];

	}

	@Override
	public double rule() {
		return a + b;
		
	}

}
