package model.action.MathOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Not extends MathOneParam {
	
	public Not (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		if (a == 0 ) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
