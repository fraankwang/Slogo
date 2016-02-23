package model.action;

public class Pow extends Action {

	double a;
	double b;

	public Pow (double a, double b) {
		super();
		this.a = a;
		this.b = b;

	}

	@Override
	public double rule() {
		return Math.pow(a, b);

	}

}
