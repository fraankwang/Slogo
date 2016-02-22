package src.model;

public class Forward extends Action {

	int pixels;
	
	public Forward(int pixels) {
		super();
		this.pixels = pixels;
		
	}

	@Override
	public int rule() {
		playground.moveTurtle(pixels);
		return 0;
	}

}
