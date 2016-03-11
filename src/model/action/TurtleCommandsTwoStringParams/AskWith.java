package model.action.TurtleCommandsTwoStringParams;

import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import model.Palette;
import model.UserCommands;
import model.Variables;
import model.turtle.Turtle;
import model.turtle.TurtlePlayground;

public class AskWith extends TurtleCommandsTwoStringParams {

	public AskWith(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super(params, language, playground, variables, usercommands, palette);
	}

	@Override
	public double rule() throws Exception {

		List<Turtle> prevActiveTurtles = new ArrayList<Turtle>(playground.getActiveTurtles());
		List<Turtle> turtleList = new ArrayList<Turtle>();

		double returnvalue = 0;
		for (Turtle turtle : playground.getTurtleList()) {
			playground.clearActiveTurtles();
			playground.setCurrentTurtle(turtle);
			try {
				if (myCommandParser.parseCommands(params).intValue() != 0) {
					turtleList.add(turtle);
				}
			} catch (Exception e) {
				throw e;
//				throw new Exception(Constants.parsingError(params));
			}
		}
		playground.clearActiveTurtles();
		playground.getActiveTurtles().addAll(turtleList);

		try {
			returnvalue = myCommandParser.parseCommands(commands);

		} catch (Exception e) {
			throw e;
//			throw new Exception(Constants.parsingError(commands));
		}
		playground.clearActiveTurtles();
		playground.getActiveTurtles().addAll(prevActiveTurtles);
		return returnvalue;
	}

}
