/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.lang.reflect.*;
import java.util.*;
import model.action.*;

public class CommandParser {
	Collection<String> myActions;
	Stack<String> stack;
	public CommandParser(){
				
	}
	
	public void parse (String input) {
		Stack<String> stack = new Stack<String>();
		stack.addAll(Arrays.asList(input.split(" ")));
	
	}
	
	public void c(){
		
	}
	
	public double checkNext() throws Exception {
		String next  = stack.pop();
		if(myActions.contains(next)){
			Class a = Class.forName(next);
			Constructor constructor = a.getConstructors()[0];
			double[] params = new double [constructor.getParameterTypes().length];
			for (int i = 0; i < params.length; i++){
				params[i] = checkNext();
			}
			
			Action action = (Action) constructor.newInstance(params);
			return action.rule();
		}
		else{
			return Double.parseDouble(next);
		}
	}
}
