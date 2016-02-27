/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

import model.action.Action;

public class UserCommands {
	
	Map<String, Action> myUserCommand;
	
	public UserCommands(){
		
	}
	
	public Collection<String> getVariables () {
		return myUserCommand.keySet();
	}
	
	public void addCommand (String name, Action action) {
		myUserCommand.put(name, action);
	}
	
	public Action getCommand (String name) {
		return myUserCommand.get(name);
	}
}
