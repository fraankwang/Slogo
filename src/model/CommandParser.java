/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import constants.Constants;
import model.action.*;

public class CommandParser {

	private String myLanguage;
	private TurtlePlayground myPlayground;
	private Variables myVariables;
	private UserCommands myUserCommands;

	private class Node {
		private String data;
		private List<Node> children;
	}

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
			if (!string.startsWith("#") && !string.isEmpty()) {
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

	private Node makeTree(Queue<String> queue) {
		Node tree = new Node();
		tree.children = new ArrayList<Node>();

		if (queue.peek().equals(Constants.OPEN_BRACKET)) {
			queue.poll();
			StringBuilder commandstring = new StringBuilder();
			while (!queue.peek().equals(Constants.CLOSE_BRACKET)) {
				String action = queue.poll();
				commandstring.append(action + " ");
			}
			queue.poll();
			tree.data = commandstring.deleteCharAt(commandstring.length() - 1).toString();
			return tree;
		} else {
			tree.data = queue.poll();
			try {
				String superclass = Class.forName(Constants.getAction(tree.data)).getSuperclass().getName();
				int totalchildren = Constants.getNumberParams(superclass);
				for (int i = 0; i < totalchildren; i++) {
					if (queue.isEmpty()) {
						throw new Exception("Too few parameters");
					}
					tree.children.add(makeTree(queue));
				}
				return tree;
			} catch (Exception exception) {
				try {
					int totalchildren = myUserCommands.getCommandParams(tree.data).size();
					for (int i = 0; i < totalchildren; i++) {
						tree.children.add(makeTree(queue));
					}
					return tree;
				} catch (Exception e) {

				}
				return tree;
			}
		}

	}

	private double treeTraversal(Node node) throws Exception {
		try {
			Action action = makeAction(node);
			return action.rule();
		} catch (Exception exception) {
			try {
				Iterator<Node> iter = node.children.iterator();

				for (String string : myUserCommands.getCommandParams(node.data)) {
					myVariables.addVariable(string, treeTraversal(iter.next()));
				}

				String thiscommand = myUserCommands.getCommand(node.data);

				return parseCommands(thiscommand);
			} catch (Exception ex) {

				if (node.children.isEmpty()) {

					if (node.data.startsWith(":")) {

						try {
							double variable = myVariables.getVariableValue(node.data);
							return variable;
						} catch (Exception unreachableVariable) {
							throw new Exception("can't get variable");
						}

					} else {
						try {
							Double parsedDouble = Double.parseDouble(node.data);
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
				finalaction = (Action) constructor.newInstance(params, myLanguage, myPlayground, myVariables, myUserCommands);
				break;
			}

			return finalaction;
		} catch (Exception e) {
			throw new Exception("Incorrect command syntax");
		}
	}

	private ArrayList<Double> addParams(Node node) throws Exception {
		ArrayList<Double> params = new ArrayList<Double>();
		if(node.children.size()>0){
			for (Node n : node.children) {
				params.add(treeTraversal(n));
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
			System.out.println("output = " + output);
		}

		return output;
	}

}