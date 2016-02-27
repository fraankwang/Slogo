package model.action.MathOneParam;

import java.util.List;

import model.Variables;
import model.action.Action;

public abstract class MathOneParam extends Action {

	protected double a;
	
	public MathOneParam(List<Double> params, Variables variables) {
		super(variables);
		this.a = params.get(0);
		
	}

}
