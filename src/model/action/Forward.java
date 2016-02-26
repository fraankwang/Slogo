package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Forward extends Action {

	Double pixels;

	public Forward(Double pixels, TurtlePlayground playground, Variables variables) {
		super(playground, variables);
		this.pixels = pixels;

	}
	public Forward(List<Double> params, TurtlePlayground playground, Variables variables) {
		this(params.get(0), playground, variables);

	}

	@Override
	public double rule() {
		playground.moveTurtle(pixels);
		return pixels;
	}

}
