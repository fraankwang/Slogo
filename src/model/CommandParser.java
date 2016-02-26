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

	private class Node<T> {
		private T data;
		private List<Node<T>> children;
	}

	private Queue<String> queue;

	public CommandParser(String language, TurtlePlayground playground, Variables variables) {
		myLanguage = language;
		myPlayground = playground;
		myVariables = variables;
	}


	private void parse(String input) {
		queue = new LinkedList<String>();
		List<String> list = Arrays.asList(input.split("\\s"));
		List<String> modified = new ArrayList<String>();
		for(String s:list){
//			System.out.println(Constants.getCommand(myLanguage, s));

			try{
				String s1 = Constants.getCommand(myLanguage, s);
				System.out.println(Constants.getCommand(myLanguage, s));

				modified.add(s1);
			}
			catch (Exception e){
				modified.add(s);
			}
		}
		queue.addAll(modified);

	}

	private Node<String> makeTree() throws Exception{
//		try{
		Node<String> tree =  new Node<String>();

//		if(queue.peek().equals(Constants.OPEN_BRACKET)){
//			StringBuilder commandstring = new StringBuilder();
//			while (!queue.peek().equals(Constants.CLOSE_BRACKET)){
//			commandstring.append(queue.poll());
//			}
//			queue.poll();
//			tree.data = commandstring.toString();
//			return tree;
//		}
//		else{
			tree.data = queue.poll();	
			tree.children = new ArrayList<Node<String>>();
			
			try{

				Class a = Class.forName(Constants.getAction(tree.data));
				
				Constructor constructor = a.getConstructors()[0];
				//??????'
				int totalchildren = constructor.getParameterTypes().length -2;
				//because variables and turtleplayground dont count
				for (int i = 0; i<totalchildren; i++){
					System.out.println("    "+Constants.getAction(tree.data)+ " "+ i);

					tree.children.add(makeTree());

				}
				return tree;
			}catch (Exception exception){
				return tree;
			}
		}

//	}

	private double treeTraversal(Node<String> node) throws Exception {
		System.out.println(node.data);
		if (node.children.isEmpty()){
			try{
				Double a = Double.parseDouble(node.data);
				return a;
			}
			catch ( Exception nfe){
				throw (NumberFormatException) nfe;
			}
		}
		else{
			ArrayList<Double> param = new ArrayList<Double>();
			for (Node n: node.children){
				param.add(treeTraversal(n));
			}
			try{
				Class a = Class.forName(Constants.getAction(node.data));
				Constructor constructor = a.getConstructors()[1];
				Action action = (Action) constructor.newInstance(param, myPlayground, myVariables);
				return action.rule();
			}
			catch (ParseException exception){
				throw exception;
			}

		}
	}

	public String parseCommands(String s) throws Exception{
		parse(s);		
		Node<String> root = makeTree();
		Double output = treeTraversal(root);
		System.out.println(output);
		return output.toString();
	}

}