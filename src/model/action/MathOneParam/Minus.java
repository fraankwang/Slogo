package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Minus extends MathOneParam {

	
	public Minus (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return -a;
	}

}