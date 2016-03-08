/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsTwoParams;

import java.util.List;
import model.turtle.TurtlePlayground;

public class SetXY extends TurtleCommandsTwoParams {
	public SetXY(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
	}

	@Override
	public double rule() {
		return playground.placeTurtle(value, param2);
	}

}
