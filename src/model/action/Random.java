package model.action;

public class Random extends Action {

	double max;
	
	public Random (double[] params) {
		super(params);
		max = params[0];

	}

	@Override
	public double rule() {
		return Math.random()*max;
	}

}
