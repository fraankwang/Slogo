package model.action;

public class Tan extends Action {

	double degrees;

	public Tan (double[] params) {
		super(params);
		degrees = params[0];

	}

	@Override
	public double rule() {
		if (degrees % 90 == 0) {
			return 0;
		}
		else {
			return Math.tan(Math.toRadians(degrees));
		}
				
	}

}
