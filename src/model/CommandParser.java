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

	/**
	 * The parse() method parses the user inputted string initially by splitting
	 * it up by its white spaces and returns a list of commands stored as a
	 * Queue<String>
	 *
	 */
	private Queue<String> parse(String input) {
		Queue<String> queue = new LinkedList<String>();
		List<String> firstParsed = Arrays.asList(input.split("\\n"));
		List<String> parsedInputList = new ArrayList<String>();

		for (String s : firstParsed) {
			if (!isComment(s)) {
				parsedInputList.addAll(Arrays.asList(s.split("\\s")));
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
		return string.contains("#");
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
			if (tree.isOpenParenthesis(queue)) {
				return makeUnlimitedParamCommand(queue, tree);
			} else {
				tree.setData(queue.poll());
				System.out.println(tree.getData());
				try {
					return addParamsToTree(tree, queue);
				} catch (Exception exception) {
					throw exception;
				}
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
			totalchildren = getNumberParams(tree.data);
			System.out.println(totalchildren + " children number");

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

	private int getNumberParams(String classname) throws ClassNotFoundException {
		int totalchildren;
		String superclass = Class.forName(Constants.getAction(classname)).getSuperclass().getName();
		totalchildren = Constants.getNumberParams(superclass);
		System.out.println(totalchildren + "total children");
		return totalchildren;
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
		Variables variablesCopy = new Variables(myVariables.getVariableMap());

		Iterator<Node> iter = node.getChildren().iterator();
		for (String string : myUserCommands.getCommandParams(node.getData())) {
			Node val = iter.next();
			myVariables.addVariable(string, treeTraversal(val));
			System.out.println("param:" + string + " , " + myVariables.getVariableValue(string));

		}
		Double returnValue = parseCommands(myUserCommands.getCommand(node.getData()));
		myVariables = variablesCopy;
		return returnValue;
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

			switch (Constants.getActionSuperClass(action.getSuperclass().getName())) {
			case "MATH_NOPARAMS":
				finalaction = (Action) constructor.newInstance();
				break;
			case "MATH_ONEPARAM":
			case "MATH_TWOPARAMS":
				finalaction = (Action) constructor.newInstance(addDoubleParams(node));
				break;
			case "TURTLE_NOCOMMANDS":
				finalaction = (Action) constructor.newInstance(myPlayground);
				break;
			case "TURTLE_ONEPARAM":
			case "TURTLE_TWOPARAMS":
				finalaction = (Action) constructor.newInstance(addDoubleParams(node), myPlayground);
				break;
			case "CONTROL_STRUCTURES":
			case "HIGHER_ORDERSTRUCTURE":
			case "TURTLE_TWOSTRINGPARAMS":
				finalaction = (Action) constructor.newInstance(addStringParams(node), myLanguage, myPlayground,
						myVariables, myUserCommands);
				break;
			case "TURTLE_ONESTRINGPARAM":
			
				finalaction = (Action) constructor.newInstance(addStringParams(node), myPlayground);
				break;
			case "TURTLE_DISPLAY_NOPARAMS":
				finalaction = (Action) constructor.newInstance(myPlayground, myPalette);
				break;
			case "TURTLE_DISPLAY_PARAMS":
			case "TURTLE_DISPLAY_FOURPARAMS":
				finalaction = (Action) constructor.newInstance(addDoubleParams(node), myPlayground, myPalette);
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
	 * The addStringParams() method returns an ArrayList<String> which contains
	 * the (children) parameters for a given node.
	 *
	 */
	private ArrayList<String> addStringParams(Node node) throws Exception {
		ArrayList<String> params = new ArrayList<String>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				if (child.areChildrenEmpty()) {
					params.add(child.getData());
				} else {
					params.add("" + treeTraversal(child));
				}
			}
		}
		return params;
	}

	/**
	 * The makeUnlimitedParamCommand() method sets a Node's value and children
	 * given a Queue <String> containing the parsed command with unlimited
	 * parameters.
	 * 
	 * @throws Exception
	 *
	 */
	private Node makeUnlimitedParamCommand(Queue<String> queue, Node tree) throws Exception {
		queue.poll();
		String command = queue.poll();
		System.out.println(command);
		if (getNumberParams(command) == 2) {
			// TODO: make this a constant
			putUnlimitedParams(command, queue, tree);
			return tree;
		} else {
			throw new Exception("Too many parameters");
		}
	}

	private Node putUnlimitedParams(String command, Queue<String> queue, Node tree) {
		String curr = queue.poll();
		if (tree.isCloseParenthesis(queue)) {
			tree.setData(curr);
			queue.poll();
		} else {
			tree.setData(command);
			tree.addChild(new Node(curr));
			tree.addChild(putUnlimitedParams(command, queue, new Node()));
		}
		return tree;

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