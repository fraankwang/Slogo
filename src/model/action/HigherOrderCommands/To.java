package model.action.HigherOrderCommands;

import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.CommandParser;
import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;

public class To extends HigherOrderCommands {

	String commandname;
	List<String> parametervariables;
	String commands;
	Variables variables;
	UserCommands usercommands;
	
	public To(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(language, playground, variables, usercommands);
		commandname = params.get(0);
		parametervariables = Arrays.asList(params.get(1).split("\\s:"));
		commands = params.get(2);
		
		this.variables = variables;
		this.usercommands = usercommands;
	}

	@Override
	public double rule() {
		try{
		 usercommands.addCommand(commandname, parametervariables, commands);
		 for(String s:parametervariables){
			 variables.addVariable(s,0);
		 }
		 return 1;
		}
		catch (Exception e){
			return 0;
		}
		 
	}

}
