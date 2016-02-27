package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Log extends MathOneParam {

	public Log (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return Math.log(a);

	}

}
