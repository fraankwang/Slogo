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

	private class Node<T> {
		private T data;
		private List<Node<T>> children;
	}

	

	public CommandParser(String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		myLanguage = language;
		myPlayground = playground;
		myVariables = variables;
		myUserCommands  = usercommands;
	}


	private  Queue<String> parse(String input) {
		 Queue<String> queue = new LinkedList<String>();
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
		return queue;

	}

	private Node<String> makeTree( Queue<String> queue) throws Exception{
//		try{
		Node<String> tree =  new Node<String>();

//		if(queue.peek().equals(Constants.OPEN_BRACKET)){
//			queue.poll();
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

					tree.children.add(makeTree(queue));

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
				try{
					Double var = myVariables.getVariableValue(node.data);
					return var;
				}
				catch ( Exception e){
					try{
						//makeactions
						
					}
					catch( Exception ex) {
						throw new Exception("wrong variable-etc");
					}
				}
				
			}
		}
		else{
			ArrayList<Double> param = new ArrayList<Double>();
			for (Node n: node.children){
				param.add(treeTraversal(n));
			}
			try{
				Class a = Class.forName(Constants.getAction(node.data));
				Constructor constructor = a.getConstructors()[0];
				Action action = (Action) constructor.newInstance(param, myPlayground, myVariables);
				return action.rule();
			}
			catch (ParseException exception){
				throw exception;
			}

		}
	}

	public Double parseCommands(String s) throws Exception{
		Queue<String> queue = parse(s);
		Double output = 0.0;
		while(!queue.isEmpty()){
			Node<String> root = makeTree(queue);
			output = treeTraversal(root);
			System.out.println(output);
		}
		
		return output;
	}

}