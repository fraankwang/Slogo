package model.action;

public class Product extends Action{
	
	private double a;
	private double b;

	public Product(double a, double b) {
		super();
		this.a = a;
		this.b = b;
		
	}

	@Override
	public double rule() {
		return a * b;
		
	}

}
