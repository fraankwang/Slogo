/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import constants.Constants;
import model.action.*;

public class CommandParser {
	private String myLanguage;


	private static class Node<T> {
		private T data;
		private List<Node<T>> children;
	}

	private Queue<String> queue;
	public CommandParser(String language) {
		myLanguage = language;
	}


	private void parse(String input) {
		queue = new LinkedList<String>();
		List<String> list = Arrays.asList(input.split(" "));
		for(String s:list){
			try{
				s = Constants.getCommand(myLanguage, s);
			}
			catch (Exception e){
			}
		}
		queue.addAll(list);

	}

	private Node<String> makeTree() throws Exception{
		try{
			Node<String> tree =  new Node<String>();

			tree.data = queue.poll();	
			tree.children = new ArrayList<Node<String>>();
			try {
				Double.parseDouble(tree.data);
				return tree;
			}
			catch (Exception e) {
				try{
					Class a = Class.forName(Constants.getAction(tree.data));
					Constructor constructor = a.getConstructors()[0];
					//??????'
					for (int i = 0; i<constructor.getParameterTypes().length; i++){
						tree.children.add(makeTree());

					}
					return tree;
				}
				catch (Exception exception){
					try{
						//						variable
					}
					catch (Exception exception2){
						System.out.println("input error");
						throw exception2;
					}
				}
			}
		}
		catch (Exception exception1){
			throw exception1;
		}
		return null;


	}

	private double treeTraversal(Node<String> node) throws Exception {
		System.out.println(node.data);
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
		double t = treeTraversal(root);
		System.out.println(t);
		return t;
	}

}