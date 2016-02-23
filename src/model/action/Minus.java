package model.action;

public class Minus extends Action {

	double a;
	
	public Minus (double a) {
		super();
		this.a = a;
		
	}

	@Override
	public double rule() {
		return -a;
	}

}
