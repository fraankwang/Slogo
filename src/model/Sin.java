package model;

public class Sin extends Action {

	double degrees;

	public Sin (double degrees) {
		super();
		this.degrees = degrees;

	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(degrees));
				
	}

}
