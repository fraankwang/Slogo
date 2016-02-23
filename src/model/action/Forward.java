package model.action;

public class Forward extends Action {

	int pixels;

	public Forward(int pixels) {
		super();
		this.pixels = pixels;

	}

	@Override
	public double rule() {
		playground.moveTurtle(pixels);
		return 0;
	}

}
