package model.action.MathTwoParams;

import model.Variables;
import model.action.Action;
import java.util.List;

public abstract class MathTwoParams extends Action {
	
	protected double a;
	protected double b;

	public MathTwoParams (List<Double> params, Variables variables) {
		super(variables);
		this.a = params.get(0);
		this.b = params.get(0);
	
	}

}
