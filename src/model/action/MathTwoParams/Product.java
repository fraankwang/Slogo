package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Product extends MathTwoParams{
	
	private double a;
	private double b;

	public Product(List<Double> params, Variables variables){
		super(params, variables);
	}

	@Override
	public double rule() {
		return a * b;
		
	}

}
