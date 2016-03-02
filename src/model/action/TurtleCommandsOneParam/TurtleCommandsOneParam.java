/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsOneParam;

import java.util.List;
import model.action.TurtleCommandsNoParams.TurtleCommands;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommandsOneParam extends TurtleCommands {

	protected double value;

	public TurtleCommandsOneParam(List<Double> params, TurtlePlayground playground) {
		super(playground);
		this.value = params.get(0);
	}

}
