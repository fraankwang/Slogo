package model.action.MathOneParam;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Sin extends MathOneParam {


	public Sin(List<Double> params, Variables variables){
		super(params, variables);
	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(a));
				
	}

}
