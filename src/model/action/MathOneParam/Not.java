package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

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
