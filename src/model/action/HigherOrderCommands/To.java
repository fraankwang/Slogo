/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model.action.HigherOrderCommands;

import java.util.*;

import constants.Constants;
import model.Palette;
import model.UserCommands;
import model.Variables;
import model.turtle.TurtlePlayground;

public class To extends HigherOrderCommands {

	String commandname;
	List<String> parametervariables;
	String commands;
	Variables variables;
	UserCommands usercommands;

	public To(List<String> params, String language, TurtlePlayground playground, Variables variables,
			UserCommands usercommands, Palette palette) {
		super(language, playground, variables, usercommands, palette);
		commandname = params.get(0);
		if (!params.get(1).isEmpty()) {
			parametervariables = Arrays.asList(params.get(1).split("\\s"));

		} else {
			parametervariables = new ArrayList<String>();
		}
		commands = params.get(2);

		this.variables = myCommandParser.getVariableList();
		this.usercommands = usercommands;
	}

	@Override
	public double rule() throws Exception {
		try {
			usercommands.addCommand(commandname, parametervariables, commands);
			System.out.println("added command ." + commands + ". with params ." + String.join(",", parametervariables)
					+ parametervariables.size());

			for (List<String> l : usercommands.getUserCommandMap().values()) {
				for (String s : l) {
					System.out.println(" command param" + s);

				}
			}

			return 1;
		} catch (Exception e) {
			System.out.println("no");
			throw new Exception(Constants.USER_PARAM_ERROR);
		}

	}

}
