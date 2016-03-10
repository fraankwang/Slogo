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

			if (isNext(queue, Constants.OPEN_BRACKET)) {
				opencount++;
			}
			if (isNext(queue, Constants.CLOSE_BRACKET)) {
				closecount++;
				if (closecount == opencount) {
					break;
				}
			}
			String action = queue.poll();
			commandstring.append(action + Constants.SPACE);
		}
		queue.poll();

		tree.data = commandstring.toString().trim();
		return tree;

	}
	


	/**
	 * The addChild() method adds a Node to the instnace of List<Node> children.
	 *
	 */
	public void addChild(Node node) {
		getChildren().add(node);
	}

	public boolean isNext(Queue<String> queue, String string) {
		return queue.peek().equals(string);
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
