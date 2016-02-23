package model.action;

public class Equal extends Action {

	private double a;
	private double b;
	
	public Equal (double[] params) {
		super(params);
		a = params[0];
		b = params[1];

	}

	@Override
	public double rule() {
		if (a == b) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
