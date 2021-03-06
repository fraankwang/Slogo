/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.MathTwoParams;

import model.action.Action;
import java.util.List;

public abstract class MathTwoParams extends Action {

	protected double a;
	protected double b;

	public MathTwoParams(List<Double> params) {
		super();
		this.a = params.get(0);
		this.b = params.get(1);

	}

}
