package model.action;

public class Atan extends Action {

	double degrees;

	public Atan (double[] params) {
		super(params);
		degrees = params[0];

	}

	@Override
	public double rule() {
		return Math.toDegrees(Math.atan(Math.toRadians(degrees)));
				
	}

}
