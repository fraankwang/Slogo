package model.action;

public class Not extends Action {

	private double a;
	
	public Not (double[] params) {
		super(params);
		a = params[0];

	}

	@Override
	public double rule() {
		if (a == 0 ) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
