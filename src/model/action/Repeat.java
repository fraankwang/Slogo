package model.action;

public class Repeat extends Action {

	private double exp;
	private Action command;

	public Repeat ( double exp, Action command) {
		super();
		this.exp = exp;
		this.command = command;

	}

	@Override
	public double rule() {
		double val = 0;
		for ( int i = 0; i <exp; i++ ){
			val = command.rule();
		}
		return val;

	}

}
