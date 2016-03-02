package model.action.MathOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Log extends MathOneParam {

	public Log (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return Math.log(a);

	}

}
