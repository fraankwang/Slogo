package model.action;

public class Log extends Action {

	double a;

	public Log (double[] params) {
		super(params);
		a = params[0];

	}

	@Override
	public double rule() {
		return Math.log(a);

	}

}
