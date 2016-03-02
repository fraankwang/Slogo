/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Not extends MathOneParam {

	public Not(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		if (a == 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
