package model.action.TurtleCommandsTwoParams;

import java.util.List;

import model.TurtlePlayground;
import model.Variables;
import model.action.Action;

public class Towards extends TurtleCommandsTwoParams{
	
	public Towards (List<Double> params, TurtlePlayground playground){
		super(params, playground);
	}
	
	
	@Override
	public double rule() {
		return playground.turnTurtle(Math.atan(value/param2));
	}
	
	

}
