/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class Difference extends MathTwoParams {

	public Difference(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.abs(a - b);

	}
}
