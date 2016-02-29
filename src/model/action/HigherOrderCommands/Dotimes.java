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

	public Dotimes ( List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		String[] arr=params.get(0).split(" ");
		variable=arr[0];
		limit=Double.parseDouble(arr[1]);
		commands=params.get(1);
		

	}

	@Override
	public double rule() {
		for ( int i = 0; i <limit; i++ ){
			try {
				Double variableValue= myCommandParser.parseCommands(params.get(1));
				myCommandParser.getVariableList().addVariable(variable, variableValue);
				return variableValue;
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		return 0;
	}

}
