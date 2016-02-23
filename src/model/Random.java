package model;

public class Random extends Action {

	double max;
	
	public Random (double max) {
		super();
		this.max = max;
		
	}

	@Override
	public double rule() {
		return Math.random()*max;
	}

}
