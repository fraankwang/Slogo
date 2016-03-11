/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import java.util.List;

import constants.Constants;
import model.Palette;
import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

public class Ifelse extends HigherOrderCommands {
	String expr;
	String command1;
	String command2;

	public Ifelse(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands,Palette palette) {
		super(language, playground, variables, usercommands,palette);
		expr = params.get(0);
		command1 = params.get(1);
		command2 = params.get(2);
	}

	@Override
	public double rule() throws Exception {
		try {
			if (myCommandParser.parseCommands(expr).intValue() != 0) {
				try {
					return myCommandParser.parseCommands(command1);
				} catch (Exception e) {
					throw new Exception(Constants.parsingError(command1));
				}
			} else {
				try {
					return myCommandParser.parseCommands(command2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception(Constants.parsingError(command2));
				}
			}
		} catch (Exception e) {
			throw new Exception(Constants.parsingError(expr));
		}
	}

}
