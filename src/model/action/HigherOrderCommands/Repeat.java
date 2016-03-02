/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import java.util.List;
import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

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
		Double curr =0.0;
		try {
			for (int repCount = 0; repCount < exp; repCount++) {

				myCommandParser.getVariableList().addVariable(":repcount", repCount);
				curr = myCommandParser.parseCommands(params.get(1));
			} 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curr;

	}

}