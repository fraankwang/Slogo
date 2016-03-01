/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import constants.Constants;
import model.action.*;
import model.Node;

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

	public Variables getVariableList() {
		return this.myVariables;
	}

	private Queue<String> parse(String input) {
		Queue<String> queue = new LinkedList<String>();
		List<String> parsedInputList = Arrays.asList(input.split("\\s"));
		List<String> comandsList = new ArrayList<String>();
		for (String string : parsedInputList) {
			if (!isComment(string) && !string.isEmpty()) {
				try {
					String command = Constants.getCommand(myLanguage, string);
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

	private boolean isComment(String string) {
		return string.startsWith("#");
	}

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

	private Action makeAction(Node node) throws Exception {
		try {
			Class action = Class.forName(Constants.getAction(node.data));
			Constructor constructor = action.getConstructors()[0];
			Action finalaction = null;

			switch (action.getSuperclass().getName()) {
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

	private ArrayList<Double> addDoubleParams(Node node) throws Exception {
		ArrayList<Double> params = new ArrayList<Double>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				params.add(treeTraversal(child));
			}
		}
		return params;
	}

	private ArrayList<String> addStringParams(Node node) throws Exception {
		ArrayList<String> params = new ArrayList<String>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				params.add(child.getData());
			}
		}
		return params;
	}

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