package model.action.TurtleCommandsTwoStringParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import constants.Constants;
import model.Palette;
import model.UserCommands;
import model.Variables;
import model.turtle.Turtle;
import model.turtle.TurtlePlayground;

public class Ask extends TurtleCommandsTwoStringParams {

	public Ask(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super(params, language, playground, variables, usercommands, palette);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rule() throws Exception {
		List<Turtle> activeTurtles = new ArrayList<Turtle>(playground.getActiveTurtles());

		playground.clearActiveTurtles();
		double returnvalue = 0;
		for (String s : Arrays.asList(params.split(" "))) {
			playground.getTurtle(Integer.parseInt(s));
			playground.getActiveTurtles().add(playground.getTurtle(Integer.parseInt(s)));
		}
		try {
			returnvalue = myCommandParser.parseCommands(commands);

		} catch (Exception e) {
			throw new Exception(Constants.parsingError(commands));

		}
		playground.clearActiveTurtles();
		playground.getActiveTurtles().addAll(activeTurtles);
		return returnvalue;
	}

}
