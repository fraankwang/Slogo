
package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public class Repeat extends ControlStructures {

	private double exp;

	public Repeat(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		//to discuss
		try {
			this.exp = myCommandParser.parseCommands(params.get(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public double rule() {

		for (int repCount = 0; repCount < exp; repCount++) {
			
			myCommandParser.getVariableList().addVariable("repCount", repCount);
			
			try {
				return myCommandParser.parseCommands(params.get(1));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0.0;

	}

}