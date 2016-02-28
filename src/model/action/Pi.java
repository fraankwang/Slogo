package model.action;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Pi extends Action {

	public Pi (List<Double> params) {
		super();
		
	}
	
	@Override
	public double rule() {
		return Math.PI;

	}

}
