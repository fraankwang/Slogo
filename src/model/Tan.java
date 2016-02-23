package model;

public class Tan extends Action {

	double degrees;

	public Tan (double degrees) {
		super();
		this.degrees = degrees;

	}

	@Override
	public double rule() {
		if (degrees % 90 == 0) {
			return 0;
		}
		else {
			return Math.tan(Math.toRadians(degrees));
		}
				
	}

}
