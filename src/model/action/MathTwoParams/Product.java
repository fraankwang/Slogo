package model.action.MathTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;

public class Product extends MathTwoParams{

	public Product(List<Double> params){
		super(params);
	}

	@Override
	public double rule() {
		return a * b;
		
	}

}
