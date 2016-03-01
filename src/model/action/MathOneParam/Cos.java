package model.action.MathOneParam;

import java.util.List;

public class Cos extends MathOneParam {

	public Cos (List<Double> params){
		super(params);
	}
	
	@Override
	public double rule() {
		return Math.cos(Math.toRadians(a));
				
	}

}
