/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.action.Action;
import model.turtle.TurtlePlayground;

public abstract class TurtleCommands extends Action {

	protected TurtlePlayground playground;

	public TurtleCommands(TurtlePlayground playground) {
		super();
		this.playground = playground;
	}

}
