package model;

public class Not extends Action {

	private double a;
	
	public Not (double a) {
		super();
		this.a = a;
		
	}

	@Override
	public double rule() {
		if (a == 0 ) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
