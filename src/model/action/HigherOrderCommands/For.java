
package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public class For extends ControlStructures{
	Double start;
	Double end;
	Double increment;
	String variable;			
	String commands;
	public For( List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);

		String[] paramsArr=params.get(0).split(" ");
		variable=paramsArr[0];
		start=Double.parseDouble(paramsArr[1]);
		end=Double.parseDouble(paramsArr[2]);
		increment=Double.parseDouble(paramsArr[3]);

		commands=params.get(1);
	}

	@Override
	public double rule() {
		for(Double beg= start; beg<end; beg+=increment){
			try {
				Double variableValue= myCommandParser.parseCommands(params.get(1));
				myCommandParser.getVariableList().addVariable(variable, variableValue);
				return variableValue;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

}