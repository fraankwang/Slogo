package model.action;

import java.util.List;

public class Forward extends Action {

	int pixels;

	public Forward(int pixels) {
		super();
		this.pixels = pixels;

	}
	public Forward(List<Double>c) {
		super();
		this.pixels = c.get(0).intValue();

	}

	@Override
	public double rule() {
		playground.moveTurtle(pixels);
		return pixels;
	}

}
