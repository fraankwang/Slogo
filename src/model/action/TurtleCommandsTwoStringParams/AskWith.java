package model.action.TurtleCommandsTwoStringParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.UserCommands;
import model.Variables;
import model.turtle.Turtle;
import model.turtle.TurtlePlayground;

public class AskWith extends TurtleCommandsTwoStringParams {

	public AskWith(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() {

		List<Turtle> prevActiveTurtles = new ArrayList<Turtle>( playground.getActiveTurtles());
		List<Turtle> turtleList = new ArrayList<Turtle>();

		double returnvalue = 0;
		for (Turtle turtle : playground.getTurtleList()) {
			playground.clearActiveTurtles();
			playground.getActiveTurtles().add(turtle);
			try {
				if(myCommandParser.parseCommands(params).intValue()!=0){
					turtleList.add(turtle);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		playground.clearActiveTurtles();
		playground.getActiveTurtles().addAll(turtleList);

		try {
			 returnvalue = myCommandParser.parseCommands(commands);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playground.clearActiveTurtles();
		playground.getActiveTurtles().addAll(prevActiveTurtles);
		return returnvalue;
	}

}
