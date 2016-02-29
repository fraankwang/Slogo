/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package model;

import java.util.*;

public class UserCommands {
	
	Map<String, String> myUserCommands;
	Map<String, List<String>> myCommandParams;
	
	public UserCommands(){
		myUserCommands = new HashMap<String, String>();
		myCommandParams = new HashMap<String, List<String>>();
	}
	
	public Collection<String> getCommands () {
		return myUserCommands.keySet();
	}
	
	public void addCommand (String name, List<String> paramvars, String commands) {
		myUserCommands.put(name, commands);
		myCommandParams.put(name, paramvars);
	}
	
	public String getCommand (String name) {
		return myUserCommands.get(name);
	}
	public List<String> getCommandParams (String name){
		return myCommandParams.get(name);
	}
	
	public Map<String, String> getUserCommandMap(){
		return myUserCommands;
	}
}
