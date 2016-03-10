package model;

import java.util.Queue;

import constants.Constants;

public class ExpressionTree {
	public UserCommands myUserCommands;

	public ExpressionTree(UserCommands usercommands) {
		myUserCommands = usercommands;

		// TODO Auto-generated constructor stub
	}

	/**
	 * The parse() method parses the user inputted string initially by splitting
	 * it up by its white spaces and returns a list of commands stored as a
	 * Queue<String>
	 *
	 */

	/**
	 * The makeTree() method assembles a tree of nodes from the list of commands
	 * stored as a Queue<String> which is returned by the parse() method.
	 *
	 */
	public Node makeTree(Queue<String> queue) throws Exception {
		Node tree = new Node();

		if (tree.nextIs(queue, Constants.OPEN_BRACKET)) {
			return tree.makeCommandString(queue, tree);
		} else {
			if (tree.nextIs(queue, Constants.OPEN_PARENTHESIS)) {
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
				throw new Exception(Constants.TOO_FEW_PARAMETERS_ERROR);
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
			throw new Exception(Constants.TOO_MANY_PARAMETERS_ERROR);
		}
	}

	private Node putUnlimitedParams(String command, Queue<String> queue, Node tree) {
		String curr = queue.poll();
		if (tree.nextIs(queue, Constants.CLOSE_PARENTHESIS)) {
			tree.setData(curr);
			queue.poll();
		} else {
			tree.setData(command);
			tree.addChild(new Node(curr));
			tree.addChild(putUnlimitedParams(command, queue, new Node()));
		}
		return tree;

	}

}
