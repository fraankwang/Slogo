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
	
	public Variables getVariableList(){
		return this.myVariables;
	}
	
	private Queue<String> parse(String input) {
		Queue<String> queue = new LinkedList<String>();
		List<String> list = Arrays.asList(input.split("\\s"));
		List<String> modified = new ArrayList<String>();
		for (String s : list) {
			// System.out.println(Constants.getCommand(myLanguage, s));
			if(!s.startsWith("#")){
				try {
					String s1 = Constants.getCommand(myLanguage, s);
					//				System.out.println(Constants.getCommand(myLanguage, s));

					modified.add(s1);
				} catch (Exception e) {
					modified.add(s);
				}
			}
			else{
				System.out.println(s);
			}
		}
		queue.addAll(modified);
		return queue;

	}

	private Node makeTree( Queue<String> queue) {
		//		try{
		Node tree =  new Node();
		tree.children = new ArrayList<Node>();


		if(queue.peek().equals(Constants.OPEN_BRACKET)){
			queue.poll();
			StringBuilder commandstring = new StringBuilder();
			while (!queue.peek().equals(Constants.CLOSE_BRACKET)){
				commandstring.append(" " +queue.poll());
			}
			queue.poll();
			tree.data = commandstring.toString();
			return tree;
		}
		else{
			tree.data = queue.poll();
			//			System.out.println("tree data "+ tree.data);
			try{
				String superclass = Class.forName(Constants.getAction(tree.data))
						.getSuperclass().getName();
				System.out.println("  super: "+superclass);
				int totalchildren = Constants.getNumberParams(superclass);
				for (int i = 0; i<totalchildren; i++){
					System.out.println("    "+Constants.getAction(tree.data)+ " + "+ i);
					if (queue.isEmpty()){
						throw new Exception("Too few parameters");
					}
					tree.children.add(makeTree(queue));
				}
				return tree;
			}catch (Exception exception){
				try{
					int totalchildren = myUserCommands.getCommandParams(tree.data).size();
					for (int i = 0; i<totalchildren; i++){
						//						System.out.println("    "+Constants.getAction(tree.data)+ " "+ i);
						// throw excp
						tree.children.add(makeTree(queue));
					}
					return tree;
				}
				catch(Exception e){

				}
				return tree;
			}
		}

	}

	private double treeTraversal(Node node) throws Exception {
		System.out.println("at node "+node.data);
		try {
			Action action = makeAction(node);
			return action.rule();
		} catch (Exception exception) {
			try{
				Iterator<Node> iter = node.children.iterator();
				for (String s: myUserCommands.getCommandParams(node.data)){
					myVariables.addVariable(s, treeTraversal(iter.next()));
				}
				return parseCommands(myUserCommands.getCommand(node.data));
			}
			catch (Exception ex){
				if (node.children.isEmpty()) {
					if (node.data.startsWith(":")){
						return myVariables.getVariableValue(node.data);
					}
					else{
						try {
							Double a = Double.parseDouble(node.data);
							return a;
						}
						catch (Exception nfe) {
							throw new Exception("Incorrect syntax for parameter");
						}
					}
				} 
				else {
					throw exception;
				}
			}

		}
	}

	private Action makeAction(Node node)
			throws Exception {
		try{
			Class action = Class.forName(Constants.getAction(node.data));
			Constructor constructor = action.getConstructors()[0];
			Action finalaction = null;

			//		System.out.println("superclass to make action: "+action.getSuperclass().getName());

			switch(action.getSuperclass().getName()){
			case "model.action.MathOneParam.MathOneParam":
			case "model.action.MathTwoParams.MathTwoParams":
				ArrayList<Double> params = new ArrayList<Double>();					
				for (Node n : node.children) {
					params.add(treeTraversal(n));
				}
				finalaction = (Action) constructor.newInstance(params);
				break;
			case "model.action.TurtleCommandsNoParams.TurtleCommands":
				finalaction = (Action) constructor.newInstance(myPlayground);

				break;
			case "model.action.TurtleCommandsOneParam.TurtleCommandsOneParam":
			case "model.action.TurtleCommandsTwoParams.TurtleCommandsTwoParams":
				ArrayList<Double> params1 = new ArrayList<Double>();					
				for (Node n : node.children) {
					params1.add(treeTraversal(n));
				}
				finalaction = (Action) constructor.newInstance(params1, myPlayground);
				break;
			case "model.action.HigherOrderCommands.ControlStructures":
			case "model.action.HigherOrderCommands.HigherOrderCommands":
				System.out.println("higher "+ node.data);

				ArrayList<String> stringparams = new ArrayList<String>();					
				for (Node n : node.children) {
					stringparams.add(n.data);
				}
				finalaction = (Action) constructor.newInstance(stringparams, myLanguage, myPlayground, myVariables, myUserCommands);
				break;
			}

			return finalaction;
		}
		catch(Exception e){
			throw new Exception("Incorrect command syntax");
		}
	}

	public Double parseCommands(String s) throws Exception {
		Queue<String> queue = parse(s);
		Double output = 0.0;
		while (!queue.isEmpty()) {
			Node root = makeTree(queue);
			output = treeTraversal(root);
			System.out.println("output = "+output);
		}


		return output;
	}

}