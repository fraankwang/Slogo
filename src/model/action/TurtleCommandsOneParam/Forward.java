/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsOneParam;

import java.util.List;

import model.turtle.TurtlePlayground;

public class Forward extends TurtleCommandsOneParam {

	public Forward(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {
		playground.moveTurtle(value);
		return value;
	}

}
