package model.action;

public class Log extends Action {

	double a;

	public Log (double a) {
		super();
		this.a = a;

	}

	@Override
	public double rule() {
		return Math.log(a);

	}

}
