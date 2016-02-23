package model.action;

public class Atan extends Action {

	double degrees;

	public Atan (double degrees) {
		super();
		this.degrees = degrees;

	}

	@Override
	public double rule() {
		return Math.toDegrees(Math.atan(Math.toRadians(degrees)));
				
	}

}
