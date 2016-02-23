package model.action;

public class Pi extends Action {

	public Pi (double[] params) {
		super(params);
		
	}

	@Override
	public double rule() {
		return Math.PI;

	}

}
