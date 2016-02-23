package model.action;

public class Or extends Action {

	private double a;
	private double b;
	
	public Or (double[] params) {
		super(params);
		a = params[0];
		b = params[1];

	}

	@Override
	public double rule() {
		if (a != 0 || b != 0) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
