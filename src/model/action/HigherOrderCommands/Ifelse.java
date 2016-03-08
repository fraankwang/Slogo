/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import java.util.List;

import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

public class Ifelse extends HigherOrderCommands {
	String expr;
	String command1;
	String command2;

	public Ifelse(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		expr = params.get(0);
		command1 = params.get(1);
		command2 = params.get(2);
	}

	@Override
	public double rule() {

		try {
			if (myCommandParser.parseCommands(expr).intValue() != 0) {
				try {
					return myCommandParser.parseCommands(command1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					return myCommandParser.parseCommands(command2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
