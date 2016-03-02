/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class Remainder extends MathTwoParams {

	public Remainder(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return a % b;

	}
}
