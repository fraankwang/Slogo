package model.action;

public class Minus extends Action {

	double a;
	
	public Minus (double[] params) {
		super(params);
		a = params[0];

	}

	@Override
	public double rule() {
		return -a;
	}

}
