
package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public class Dotimes extends ControlStructures {

	private String variable;
	private double limit;
	String commands;

	public Dotimes(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		String[] arr = params.get(0).split(" ");
		variable = arr[0];
		try {
			limit = myCommandParser.parseCommands(params.get(0).replace(variable, ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commands = params.get(1);

	}

	@Override
	public double rule() {
		double curr = 0.0;

		for (int i = 0; i < limit; i++) {
			try {
				myCommandParser.getVariableList().addVariable(variable, i);

				Double variableValue = myCommandParser.parseCommands(commands);
				curr = variableValue;

			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return curr;
	}

}
