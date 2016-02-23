package model.action;

public class Cos extends Action {

	double degrees;

	public Cos (double degrees) {
		super();
		this.degrees = degrees;

	}

	@Override
	public double rule() {
		return Math.cos(Math.toRadians(degrees));
				
	}

}
