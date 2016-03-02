/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Minus extends MathOneParam {

	public Minus(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return -a;
	}

}
