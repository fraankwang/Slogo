/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import constants.Constants;
import model.action.*;
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

	public CommandParser(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		myLanguage = language;
		myPlayground = playground;
		myVariables = variables;
		myUserCommands = usercommands;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public Variables getVariableList() {
		return this.myVariables;
	}

	/**
	 * The parse() method parses the user inputted string initially by splitting
	 * it up by its white spaces and returns a list of commands stored as a
	 * Queue<String>
	 *
	 */
	private Queue<String> parse(String input) {
		Queue<String> queue = new LinkedList<String>();
		List<String> parsedInputList = Arrays.asList(input.split("\\s"));
		List<String> comandsList = new ArrayList<String>();
		for (String string : parsedInputList) {
			if (!isComment(string) && !string.isEmpty()) {
				try {
					String command = Constants.getCommand(myLanguage, string);
					if (command.startsWith("\"")) {
						command = command.substring(1, command.length() - 1);
					}
					comandsList.add(command);
				} catch (Exception e) {
					comandsList.add(string);
				}
			} else {
				System.out.println(string);
			}
		}
		queue.addAll(comandsList);
		return queue;

	}

	/**
	 * The isComment() method checks whether a string is a comment by returning
	 * a boolean for whether the string starts with a #
	 */
	private boolean isComment(String string) {
		return string.startsWith("#");
	}

	/**
	 * The makeTree() method assembles a tree of nodes from the list of commands
	 * stored as a Queue<String> which is returned by the parse() method.
	 *
	 */
	private Node makeTree(Queue<String> queue) throws Exception {
		Node tree = new Node();
		if (tree.isOpenBracket(queue)) {
			return tree.makeCommandString(queue, tree);
		} else {
			tree.setData(queue.poll());
			System.out.println(tree.getData());
			try {
				addParamsToTree(tree, queue);
				return tree;
			} catch (Exception exception) {
				throw exception;
			}
		}
	}

	/**
	 * The addParamsToTree() method adds parameter children nodes to the Tree
	 * while making it, while catching exceptions for whether there are too few
	 * children.
	 *
	 */
	private Node addParamsToTree(Node tree, Queue<String> queue) throws Exception {
		int totalchildren = 0;

		try {
			String superclass = Class.forName(Constants.getAction(tree.data)).getSuperclass().getName();
			totalchildren = Constants.getNumberParams(superclass);

		} catch (Exception e) {
			try {
				totalchildren = myUserCommands.getCommandParams(tree.getData()).size();
			} catch (Exception ex) {
			}
		}
		for (int i = 0; i < totalchildren; i++) {
			if (queue.isEmpty()) {
				throw new Exception("Too few parameters");
			}
			tree.addChild(makeTree(queue));
			System.out.println("child" + tree.getChildren().get(i).getData());
		}

		return tree;
	}

	/**
	 * The treeTraversal() method recursively traverses up the tree constructed
	 * by makeTree() in order to parse it.
	 *
	 */
	private double treeTraversal(Node node) throws Exception {
		System.out.println("at node: " + node.getData());
		try {
			Action action = makeAction(node);
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
	 * The parseValue() method returns the parsed value of a node.
	 *
	 */
	private double parseValue(Node node) throws Exception {
		if (node.isVariable()) {
			try {
				double variable = myVariables.getVariableValue(node.getData());
				return variable;
			} catch (Exception unreachableVariable) {
				throw new Exception("can't get variable");
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
		Iterator<Node> iter = node.getChildren().iterator();
		System.out.println(" size " + myUserCommands.getCommandParams(node.getData()).size());
		for (String string : myUserCommands.getCommandParams(node.getData())) {
			System.out.println(" param:" + string);
			Node val = iter.next();
			System.out.println(" value" + val.getData());
			myVariables.addVariable(string, treeTraversal(val));
			System.out.println("param:" + string + " , " + myVariables.getVariableValue(string));

		}

		String thiscommand = myUserCommands.getCommand(node.getData());

		return parseCommands(thiscommand);
	}

	/**
	 * The makeAction() method returns an action given a node by constructing a
	 * new instance of it utilizing the parameters passed in.
	 *
	 */
	private Action makeAction(Node node) throws Exception {
		try {
			Class action = Class.forName(Constants.getAction(node.data));
			Constructor constructor = action.getConstructors()[0];
			Action finalaction = null;
			System.out.println(action.getSuperclass().getName());

			switch (action.getSuperclass().getName()) {
			case Constants.MATH_NOPARAMS:
				finalaction = (Action) constructor.newInstance();
				break;
			case Constants.MATH_ONEPARAM:
			case Constants.MATH_TWOPARAMS:
				finalaction = (Action) constructor.newInstance(addDoubleParams(node));
				break;
			case Constants.TURTLE_NOCOMMANDS:
				finalaction = (Action) constructor.newInstance(myPlayground);
				break;
			case Constants.TURTLE_ONEPARAM:
			case Constants.TURTLE_TWOPARAMS:
				finalaction = (Action) constructor.newInstance(addDoubleParams(node), myPlayground);
				break;
			case Constants.CONTROL_STRUCTURES:
			case Constants.HIGHER_ORDERSTRUCTURE:
				finalaction = (Action) constructor.newInstance(addStringParams(node), myLanguage, myPlayground,
						myVariables, myUserCommands);
				break;
			}

			return finalaction;
		} catch (Exception e) {
			throw new Exception("Incorrect command syntax");
		}
	}

	/**
	 * The addDoubleParams() method returns an ArrayList<Double> which contains
	 * the (children) parameters for a given node.
	 *
	 */
	private ArrayList<Double> addDoubleParams(Node node) throws Exception {
		ArrayList<Double> params = new ArrayList<Double>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				params.add(treeTraversal(child));
			}
		}
		return params;
	}

	/**
	 * The addDoubleParams() method returns an ArrayList<String> which contains
	 * the (children) parameters for a given node.
	 *
	 */
	private ArrayList<String> addStringParams(Node node) throws Exception {
		ArrayList<String> params = new ArrayList<String>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				params.add(child.getData());
			}
		}
		return params;
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
			Node root = makeTree(queue);
			output = treeTraversal(root);
		}
		System.out.println("output: " + output);
		return output;
	}

}