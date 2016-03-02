package model.action.MathOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class Sin extends MathOneParam {


	public Sin(List<Double> params){
		super(params);
	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(a));
				
	}

}
