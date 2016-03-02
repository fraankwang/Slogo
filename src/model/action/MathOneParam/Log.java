/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathOneParam;

import java.util.List;

public class Log extends MathOneParam {

	public Log(List<Double> params) {
		super(params);
	}

	@Override
	public double rule() {
		return Math.log(a);

	}

}
