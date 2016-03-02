/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Atan extends MathOneParam {

	public Atan(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.toDegrees(Math.atan(Math.toRadians(a)));

	}

}
