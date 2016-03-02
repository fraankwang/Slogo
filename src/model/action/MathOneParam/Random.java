/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Random extends MathOneParam {

	public Random(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.random() * a;
	}

}
