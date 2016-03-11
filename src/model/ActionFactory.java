package model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import constants.Constants;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class ActionFactory {
	public String myLanguage;
	public TurtlePlayground myPlayground;
	public Variables myVariables;
	public UserCommands myUserCommands;
	public Palette myPalette;

	public ActionFactory(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands, Palette palette) {
		myLanguage = language;
		myPlayground = playground;
		myVariables = variables;
		myUserCommands = usercommands;
		myPalette = palette;

	}

	/**
	 * The makeAction() method returns an action given a node by constructing a
	 * new instance of it utilizing the parameters passed in.
	 *
	 * 
	 * 
	 */

	public Action makeAction(Node node, List<String> stringparams) throws Exception {
		try {
			Class action = Class.forName(Constants.getAction(node.data));
			Constructor constructor = action.getConstructors()[0];
			Action finalaction = null;
			System.out.println(action.getSuperclass().getName());

			switch (Constants.getActionSuperClass(action.getSuperclass().getName())) {
			case "MATH_NOPARAMS":
				finalaction = (Action) constructor.newInstance();
				break;
			case "MATH_ONEPARAM":
			case "MATH_TWOPARAMS":
				finalaction = (Action) constructor.newInstance(stringToDoubleParams(stringparams));
				break;
			case "TURTLE_NOCOMMANDS":
				finalaction = (Action) constructor.newInstance(myPlayground);
				break;
			case "TURTLE_ONEPARAM":
			case "TURTLE_TWOPARAMS":
				finalaction = (Action) constructor.newInstance(stringToDoubleParams(stringparams), myPlayground);
				break;
			case "CONTROL_STRUCTURES":
			case "HIGHER_ORDERSTRUCTURE":
			case "TURTLE_TWOSTRINGPARAMS":
				finalaction = (Action) constructor.newInstance(stringparams, myLanguage, myPlayground, myVariables,
						myUserCommands, myPalette);
				break;
			case "TURTLE_ONESTRINGPARAM":

				finalaction = (Action) constructor.newInstance(stringparams, myPlayground);
				break;
			case "TURTLE_DISPLAY_NOPARAMS":
				finalaction = (Action) constructor.newInstance(myPlayground, myPalette);
				break;
			case "TURTLE_DISPLAY_PARAMS":
			case "TURTLE_DISPLAY_FOURPARAMS":
				finalaction = (Action) constructor.newInstance(stringToDoubleParams(stringparams), myPlayground,
						myPalette);
				break;

			}

			return finalaction;
		} catch (Exception e) {
			throw new Exception(Constants.SYNTAX_ERROR);
		}
	}

	private List<Double> stringToDoubleParams(List<String> stringparams) {
		return stringparams.stream().map(s -> Double.parseDouble(s)).collect(Collectors.toList());

	}

}