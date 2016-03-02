/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Tan extends MathOneParam {

	public Tan(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		if (a % 90 == 0) {
			return 0;
		} else {
			return Math.tan(Math.toRadians(a));
		}

	}

}
