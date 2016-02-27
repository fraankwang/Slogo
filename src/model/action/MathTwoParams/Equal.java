package model.action.MathTwoParams;

import java.util.List;
import model.Variables;

public class Equal extends MathTwoParams {


	public Equal (List<Double> params, Variables variables){
		super(params, variables);
	}
	

	@Override
	public double rule() {
		if (a == b) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
