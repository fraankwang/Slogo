/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import constants.Constants;
import model.action.*;

public class CommandParser {
	private Stack<String> stack;
	public CommandParser() {

	}

	private void parse(String input) {
		Stack<String> stack = new Stack<String>();
		stack.addAll(Arrays.asList(input.split(" ")));

	}

	private static class Node<T> {
		private T data;
		private Node<T> parent;
		private List<Node<T>> children;
	}


	private Node<String> makeTree() throws Exception{
		//if object
		//check number of children
		//for i<children add children(stack.pop)
		//if var put string and later (when collapsing) get value
		//if child, it's done

		//do errors in the 


		Node<String> tree =  new Node<String>();
		tree.data = stack.pop();
		try {
			Double.parseDouble(tree.data);
			return tree;
		}
		catch (Exception e) {
			try{
				Class a = Class.forName(Constants.getAction(tree.data));
				Constructor constructor = a.getConstructors()[0];
				for (int i = 0; i<constructor.getParameterTypes().length; i++){
					tree.children.add(makeTree());

				}
				return tree;
			}
			catch (Exception exception){
				throw exception;
			}


		}

	}

	private double treeTraversal(Node<String> node) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (node.children.isEmpty()){
			return Double.parseDouble(node.data);
		}
		else{
			ArrayList<Double> param = new ArrayList<Double>();
			for (Node n: node.children){
				param.add(treeTraversal(n));
			}
			Class a = Class.forName(Constants.getAction(node.data));
			Constructor constructor = a.getConstructors()[1];
			Action action = (Action) constructor.newInstance(param);
			return action.rule();

		}
	}

	public double parseCommands(String s) throws Exception{
		parse(s);
		Node<String> root = makeTree();
		return treeTraversal(root);
	}

}
