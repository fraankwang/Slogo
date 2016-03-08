/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsOneParam;

import java.util.List;
import model.turtle.TurtlePlayground;

public class SetHeading extends TurtleCommandsOneParam {

	public SetHeading(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
	}

	@Override
	public double rule() {
		Double difference = playground.getCurrentTurtle().getOrientation() - value;
		playground.getCurrentTurtle().setOrientation(value);
		return difference;
	}
}
