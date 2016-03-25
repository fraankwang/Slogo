// This entire file is part of my masterpiece.
// HUIJIA YU
// Since this is used by the action "To", I also added it: 
// it is an example of encapsulating implementation in an object.


/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

/**
 * The UserCommands class is a custom object which holds user-defined commands
 * The UserCommands class has an instance of a Map<String, String>
 * myUserCommands The UserCommmands class has an instance of Map<String, List
 * <String>> myCommandParams
 * 
 */
public class UserCommands {

	Map<String, String> myUserCommands;
	Map<String, List<String>> myCommandParams;

	public UserCommands() {
		myUserCommands = new HashMap<String, String>();
		myCommandParams = new HashMap<String, List<String>>();
	}

	/**
	 * The addCommands() method adds a command to the UserCommands class Given a
	 * String name, a List<String> paramvars, and a String commands The
	 * addCommands() method adds (name,commands) to myUserCommands The
	 * addCommands() method adds (name,paramvars) to myCommandParams
	 */
	public void addCommand(String name, List<String> paramvars, String commands) {
		myUserCommands.put(name, commands);
		myCommandParams.put(name, paramvars);
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public String getCommand(String name) {
		return myUserCommands.get(name);
	}

	public List<String> getCommandParams(String name) {
		return myCommandParams.get(name);
	}

	public Map<String, List<String>> getUserCommandMap() {
		return myCommandParams;
	}

	public Collection<String> getCommands() {
		return myUserCommands.keySet();
	}
}
