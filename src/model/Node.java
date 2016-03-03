/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import constants.Constants;

/**
 * The Node class is a custom object which represents a Node in the tree which
 * is constructed by the CommandParser class. The Node class has an instance of
 * a String variable data and a List<Node> children. The data variable
 * represents the node's value. The children variable represents a list of
 * children nodes.
 */
public class Node {
	String data;
	List<Node> children;

	public Node(String dataString, List<Node> childList) {
		data = dataString;
		children = childList;

	}
	public Node(String dataString){
		data = dataString;
		children = new ArrayList<Node>();

	}

	public Node() {
		data = new String();
		children = new ArrayList<Node>();
	}

	/**
	 * The makeCommandString() method sets a Node's value given a Queue
	 * <String> containing the parsed user commands.
	 *
	 */
	public Node makeCommandString(Queue<String> queue, Node tree) {
		queue.poll();
		StringBuilder commandstring = new StringBuilder();
		int opencount = 1;
		int closecount = 0;
		while (true) {

			if (isOpenBracket(queue)) {
				opencount++;
			}
			if (isCloseBracket(queue)) {
				closecount++;
				if (closecount == opencount) {
					break;
				}
			}
			String action = queue.poll();
			commandstring.append(action + " ");
		}
		queue.poll();

		tree.data = commandstring.toString().trim();
		return tree;

	}
	/**
	 * The makeUnlimitedParamCommand() method sets a Node's value and children given a Queue
	 * <String> containing the parsed command with unlimited parameters.
	 *
	 */
	public Node makeUnlimitedParamCommand(Queue<String> queue, Node tree) {
		queue.poll();
		String command = queue.poll();

		putUnlimitedParams(command, queue, tree);

		return tree;
	}

	private Node putUnlimitedParams(String command, Queue<String> queue, Node tree){
		String curr = queue.poll();
		if(isCloseParenthesis(queue)){
			tree.setData(curr);
		}
		else{
			tree.setData(command);
			tree.addChild(new Node(curr));
			tree.addChild(putUnlimitedParams(command, queue, new Node()));
		}
		return tree;

	}


	/**
	 * The addChild() method adds a Node to the instnace of List<Node> children.
	 *
	 */
	public void addChild(Node node) {
		getChildren().add(node);
	}

	public boolean isOpenBracket(Queue<String> queue) {
		return queue.peek().equals(Constants.OPEN_BRACKET);
	}

	public boolean isCloseBracket(Queue<String> queue) {
		return queue.peek().equals(Constants.CLOSE_BRACKET);
	}
	public boolean isOpenParenthesis(Queue<String> queue) {
		return queue.peek().equals(Constants.OPEN_PARENTHESIS);
	}
	public boolean isCloseParenthesis(Queue<String> queue) {
		return queue.peek().equals(Constants.CLOSE_PARENTHESIS);
	}
	public boolean areChildrenEmpty() {
		return getChildren().isEmpty();
	}

	public boolean isVariable() {
		System.out.println("  variable!");
		return getData().startsWith(":");
	}
	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public String getData() {
		return this.data;
	}

	public List<Node> getChildren() {
		return this.children;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}



}
