/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.TurtleCommandsNoParams;

import model.turtle.TurtlePlayground;

public class Home extends TurtleCommands {

	public Home(TurtlePlayground playground) {
		super(playground);
	}

	public double rule() {
		return playground.setTurtleHome();
	}

}
