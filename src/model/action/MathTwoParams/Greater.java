/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class Greater extends MathTwoParams {

	public Greater(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		if (a > b) {
			return 1;
		} else {
			return 0;
		}
	}

}
