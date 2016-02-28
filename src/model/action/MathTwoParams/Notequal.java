package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Notequal extends MathTwoParams {

	
	public Notequal (List<Double> params){
		super(params);
	}
	

	@Override
	public double rule() {
		if (a != b) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
