package model.action;

public class Make extends Action {

	private String var;
	private double exp;

	public Make (String var, double exp) {
		super();
		this.var = var;
		this.exp = exp;

	}

	@Override
	public double rule() {
		variables.addVariable(var, exp);
		return exp;
		
	}

}
