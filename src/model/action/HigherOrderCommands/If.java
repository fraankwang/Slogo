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

public class If extends ControlStructures {

	public If(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super(params, language, playground, variables, usercommands,palette);

	}

	@Override
	public double rule() throws Exception {
		try {
			if (myCommandParser.parseCommands(params.get(0)) != 0) {
				System.out.println("run " + params.get(1));
				try {
					return myCommandParser.parseCommands(params.get(1));
				} catch (Exception e) {
					throw new Exception(Constants.parsingError(params.get(1)));
				}
			}
		} catch (Exception e) {
			throw new Exception(Constants.parsingError(params.get(0)));

		}
		return 0;
	}
}
