package model.action;

public class Pow extends Action {

	double a;
	double b;

	public Pow (double[] params) {
		super(params);
		a = params[0];
		b = params[1];

	}


	@Override
	public double rule() {
		return Math.pow(a, b);

	}

}
