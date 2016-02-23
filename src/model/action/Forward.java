package model.action;

public class Forward extends Action {

	double pixels;

	public Forward(double[] params) {
		super(params);
		pixels = params[0];

	}

	@Override
	public double rule() {
		playground.moveTurtle((int) pixels);
		return 0;
	}

}
