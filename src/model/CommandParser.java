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

	public void parse(String input) {
		Stack<String> stack = new Stack<String>();
		stack.addAll(Arrays.asList(input.split(" ")));

	}

	public void makeTree(){
		//if object
		//check number of children
		//for i<children add children(stack.pop)
		//if var put string and later (when collapsing) get value
		//if child, it's done
		
		//do errors in the 
		
		TreeNode tree = new TreeNode();
		
	}
	
	
	public double checkNext() throws Exception {
		String next = stack.pop();

		try{
			//??????? also language?
			//decide array vs indiv params
//			make interface for api
//			try-catch blocks
//			how to resources--maybe have a local thing?
//			variables--figure out how to re-make stuff?
			
			
			Class a = Class.forName(Constants.getAction(next));
			Constructor constructor = a.getConstructors()[0];
			double[] params = new double[constructor.getParameterTypes().length];
			for (int i = 0; i < params.length; i++) {
				params[i] = checkNext();
			}

			Action action = (Action) constructor.newInstance(params);
			return action.rule();
		}
		catch (Exception e){
			throw e ;
		}
		finally {
			try {
				return Double.parseDouble(next);
			}
			catch (Exception e){
//				throw e;
			}
		}

	}
}