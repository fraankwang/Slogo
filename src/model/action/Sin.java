package model.action;

public class Sin extends Action {

	double degrees;

	public Sin (double[] params) {
		super(params);
		degrees = params[0];

	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(degrees));
				
	}

}
