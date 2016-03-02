/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import constants.Constants;

public class Node {
	String data;
	List<Node> children;

	public Node(String dataString, List<Node> childList) {
		data = dataString;
		children = childList;

	}

	public Node() {
		data = new String();
		children = new ArrayList<Node>();
	}

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

	public boolean isOpenBracket(Queue<String> queue) {
		return queue.peek().equals(Constants.OPEN_BRACKET);
	}

	public boolean isCloseBracket(Queue<String> queue) {
		return queue.peek().equals(Constants.CLOSE_BRACKET);
	}

	public void addChild(Node node) {
		getChildren().add(node);
	}

	public boolean areChildrenEmpty() {
		return getChildren().isEmpty();
	}

	public boolean isVariable() {
		System.out.println("  variable!");
		return getData().startsWith(":");
	}
}
