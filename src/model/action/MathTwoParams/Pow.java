/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import java.util.List;

public class Pow extends MathTwoParams {

	public Pow(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.pow(a, b);

	}

}
