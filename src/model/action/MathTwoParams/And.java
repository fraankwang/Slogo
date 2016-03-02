/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class And extends MathTwoParams {

	public And(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		if (a != 0 && b != 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
