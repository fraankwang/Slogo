package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Atan extends MathOneParam {


	public Atan (List<Double> params){
		super(params);
	}

	@Override
	public double rule() {
		return Math.toDegrees(Math.atan(Math.toRadians(a)));
				
	}

}