/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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

	private Node makeTree(Queue<String> queue) {
		Node tree = new Node();

		if (tree.isOpenBracket(queue)) {
			return tree.makeCommandString(queue, tree);
		} else {
			tree.setData(queue.poll());
			try {
				addParamsToTree(tree, queue);
				return tree;
			} catch (Exception exception) {
				try {
					addNonCommandParamsToTree(tree, queue);
				} catch (Exception e) {

				}
				return tree;
			}
		}

	}

	private Node addParamsToTree(Node tree, Queue<String> queue) throws Exception {
		String superclass = Class.forName(Constants.getAction(tree.data)).getSuperclass().getName();
		int totalchildren = Constants.getNumberParams(superclass);
		for (int i = 0; i < totalchildren; i++) {
			if (queue.isEmpty()) {
				throw new Exception("Too few parameters");
			}
			tree.addChild(makeTree(queue));
		}
		return tree;
	}

	private Node addNonCommandParamsToTree(Node tree, Queue<String> queue) {
		int totalchildren = myUserCommands.getCommandParams(tree.data).size();
		for (int i = 0; i < totalchildren; i++) {
			tree.addChild(makeTree(queue));
		}
		return tree;
	}

	private double treeTraversal(Node node) throws Exception {
		try {
			Action action = makeAction(node);
			return action.rule();
		} catch (Exception exception) {
			try {
				return parseUserCommands(node);
			} catch (Exception ex) {

				if (node.areChildrenEmpty()) {

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
				} else {
					throw exception;
				}
			}

		}
	}

	private Double parseUserCommands(Node node) throws Exception {
		Iterator<Node> iter = node.getChildren().iterator();

		for (String string : myUserCommands.getCommandParams(node.getData())) {
			myVariables.addVariable(string, treeTraversal(iter.next()));
		}

		String thiscommand = myUserCommands.getCommand(node.getData());

		return parseCommands(thiscommand);
	}

	private Action makeAction(Node node) throws Exception {
		try {
			Class action = Class.forName(Constants.getAction(node.data));
			Constructor constructor = action.getConstructors()[0];
			Action finalaction = null;
			ArrayList<Double> params = addParams(node);

			switch (action.getSuperclass().getName()) {
			case Constants.MATH_ONEPARAM:
			case Constants.MATH_TWOPARAMS:
				finalaction = (Action) constructor.newInstance(params);
				break;
			case Constants.TURTLE_NOCOMMANDS:
				finalaction = (Action) constructor.newInstance(myPlayground);
				break;
			case Constants.TURTLE_ONEPARAM:
			case Constants.TURTLE_TWOPARAMS:
				finalaction = (Action) constructor.newInstance(params, myPlayground);
				break;
			case Constants.CONTROL_STRUCTURES:
			case Constants.HIGHER_ORDERSTRUCTURE:
				finalaction = (Action) constructor.newInstance(params, myLanguage, myPlayground, myVariables,
						myUserCommands);
				break;
			}

			return finalaction;
		} catch (Exception e) {
			throw new Exception("Incorrect command syntax");
		}
	}

	private ArrayList<Double> addParams(Node node) throws Exception {
		ArrayList<Double> params = new ArrayList<Double>();
		if (node.children.size() > 0) {
			for (Node child : node.children) {
				params.add(treeTraversal(child));
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

		return output;
	}

}