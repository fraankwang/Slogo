/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Sin extends MathOneParam {

	public Sin(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.sin(Math.toRadians(a));

	}

}
