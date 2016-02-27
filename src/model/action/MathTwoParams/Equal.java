package model.action.MathTwoParams;

import java.util.List;
import model.Variables;

public class Equal extends MathTwoParams {


	public Equal (List<Double> params){
		super(params);
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
