/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class Sum extends MathTwoParams {

	public Sum(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return a + b;

	}

}
