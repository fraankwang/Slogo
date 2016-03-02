/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.turtle.TurtlePlayground;

public class Towards extends TurtleCommandsTwoParams {

	public Towards(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
	}

	@Override
	public double rule() {
		return playground.turnTurtle(Math.atan(value / param2));
	}

}
