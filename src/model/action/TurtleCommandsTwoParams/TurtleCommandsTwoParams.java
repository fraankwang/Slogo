/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.action.TurtleCommandsOneParam.TurtleCommandsOneParam;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsTwoParams extends TurtleCommandsOneParam {
	protected double param2;

	public TurtleCommandsTwoParams(List<Double> params, TurtlePlayground playground) {
		super(params, playground);
		this.param2 = params.get(1);
	}
}
