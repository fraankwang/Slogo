package model.action;

public class Cos extends Action {

	double degrees;

	public Cos (double[] params) {
		super(params);
		degrees = params[0];

	}

	@Override
	public double rule() {
		return Math.cos(Math.toRadians(degrees));
				
	}

}
