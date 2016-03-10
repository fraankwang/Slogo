/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import java.util.List;
import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

public class For extends ControlStructures {
	Double start;
	Double end;
	Double increment;
	String variable;
	String commands;

	public For(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);

		String[] paramsArr = params.get(0).split(" ");

		variable = paramsArr[0];
		try {
			start = myCommandParser.parseCommands(paramsArr[1]);
			end = myCommandParser.parseCommands(paramsArr[2]);
			increment = myCommandParser.parseCommands(paramsArr[3]);
		} catch (Exception e) {

		}

		commands = params.get(1);
	}

	@Override
	public double rule() {
		double curr = 0.0;
		try {
			for (Double beg = start; beg < end; beg += increment) {

				Double variableValue = myCommandParser.parseCommands(params.get(1));
				myCommandParser.getVariableList().addVariable(variable, variableValue);
				curr = variableValue;

			}
		} catch (Exception e) {
		
		}
		return curr;
	}

}