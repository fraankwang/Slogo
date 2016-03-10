/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import constants.Constants;
import model.action.*;
import model.turtle.TurtlePlayground;
import model.Node;

/**
 * The CommandParser class is a custom object which parses the user inputted
 * string in order to call the correction Action from the Action hierarchy so
 * the rules() method can be returned. The CommandParser object has an instance
 * of String myLanguage, an instance of TurtlePlaygournd myPlayground, Variables
 * myVariables, and UserCommands myUserCommands.
 * 
 */
public class CommandParser {

	private String myLanguage;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	private UserCommands myUserCommands;
	private Palette myPalette;
	private ActionFactory myActionFactory = new ActionFactory(myLanguage, myPlayground, myVariables, myUserCommands);
	private ExpressionTree myExpressionTree = new ExpressionTree(myUserCommands);

	public CommandParser(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		myLanguage = language;
		myPlayground = playground;
		myVariables = variables;
		myUserCommands = usercommands;
	}

	public CommandParser(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands,
			Palette palette) {
		this(language, playground, variables, usercommands);
		myPalette = palette;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public Variables getVariableList() {
		return this.myVariables;
	}

	private Queue<String> parse(String input) {
		Queue<String> queue = new LinkedList<String>();
		List<String> firstParsed = Arrays.asList(input.split(Constants.WHITESPACE));
		List<String> parsedInputList = new ArrayList<String>();

		for (String s : firstParsed) {
			if (!isComment(s)) {
				parsedInputList.addAll(Arrays.asList(s.split(Constants.NEWLINE)));
			}
		}

		List<String> comandsList = new ArrayList<String>();
		for (String string : parsedInputList) {
			if (!isComment(string) && !string.isEmpty()) {
				try {
					String command = Constants.getCommand(myLanguage, string);
					comandsList.add(command);
				} catch (Exception e) {
					comandsList.add(string);
				}
			}
			// else {
			// System.out.println(string);
			// }
		}
		queue.addAll(comandsList);
		return queue;

	}

	/**
	 * The isComment() method checks whether a string is a comment by returning
	 * a boolean for whether the string starts with a #
	 */
	private boolean isComment(String string) {
		return string.contains(Constants.HASHTAG);
	}

	/**
	 * The treeTraversal() method recursively traverses up the tree constructed
	 * by makeTree() in order to parse it.
	 *
	 */
	private double treeTraversal(Node node) throws Exception {
		System.out.println("at node: " + node.getData());
		try {
			List<String> stringparams = new ArrayList<String>();
			List<Double> doubleparams = new ArrayList<Double>();
			if (node.children.size() > 0) {
				stringparams = addStringParams(node);
				doubleparams = addDoubleParams(node);
			}

			Action action = myActionFactory.makeAction(node, stringparams, doubleparams);
			System.out.println(action.getClass().getName());
			return action.rule();
		} catch (Exception exception) {
			try {
				return parseUserCommands(node);
			} catch (Exception ex) {

				if (node.areChildrenEmpty()) {
					return parseValue(node);
				} else {
					throw exception;
				}
			}

		}
	}

	/**
	 * The addDoubleParams() method returns an ArrayList<Double> which contains
	 * the (children) parameters for a given node.
	 *
	 */
	private ArrayList<Double> addDoubleParams(Node node) throws Exception {
		ArrayList<Double> params = new ArrayList<Double>();
		for (Node child : node.children) {
			params.add(treeTraversal(child));
		}

		return params;
	}

	/**
	 * The addStringParams() method returns an ArrayList<String> which contains
	 * the (children) parameters for a given node.
	 *
	 */
	private ArrayList<String> addStringParams(Node node) throws Exception {
		ArrayList<String> params = new ArrayList<String>();
		for (Node child : node.children) {
			if (child.areChildrenEmpty()) {
				params.add(child.getData());
			} else {
				params.add(Double.toString(treeTraversal(child)));
			}

		}
		return params;
	}

	/**
	 * The parseValue() method returns the parsed value of a node.
	 *
	 */
	private double parseValue(Node node) throws Exception {
		if (node.isVariable()) {
			try {
				double variable = myVariables.getVariableValue(node.getData());
				return variable;
			} catch (Exception unreachableVariable) {
				// throw new Exception("can't get variable");
				myVariables.addVariable(node.getData(), Constants.DEFAULT_VARIABLE_VALUE);
				return Constants.DEFAULT_VARIABLE_VALUE;
			}

		} else {
			try {
				Double parsedDouble = Double.parseDouble(node.getData());
				return parsedDouble;
			} catch (Exception incorrectSyntax) {
				throw new Exception("Incorrect syntax for parameter");
			}
		}
	}

	/**
	 * The parseUserCommands() method returns the parsed value of a user
	 * command.
	 *
	 */
	private Double parseUserCommands(Node node) throws Exception {
		Map<String, Double> variablesCopy = new HashMap<String, Double>(myVariables.getVariableMap());

		Iterator<Node> iter = node.getChildren().iterator();
		for (String string : myUserCommands.getCommandParams(node.getData())) {
			Node val = iter.next();
			myVariables.addVariable(string, treeTraversal(val));
			System.out.println("param:" + string + " , " + myVariables.getVariableValue(string));

		}
		Double returnValue = parseCommands(myUserCommands.getCommand(node.getData()));
		myVariables.setVariableMap(variablesCopy);
		return returnValue;
	}

	/**
	 * The parseCommands() method puts all of the other private helper methods
	 * together in order to parse a user-inputted string into a Double output.
	 * The method parses the string, constructs a tree, and traverses it in
	 * order to return the output.
	 * 
	 */
	public Double parseCommands(String s) throws Exception {
		Queue<String> queue = parse(s);
		Double output = 0.0;
		while (!queue.isEmpty()) {
			Node root = myExpressionTree.makeTree(queue);
			output = treeTraversal(root);
		}
		System.out.println("output: " + output);
		return output;
	}

}