package model.action.HigherOrderCommands;

import java.util.List;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;

public class Ifelse extends ControlStructures {

	public Ifelse(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
	}

	@Override
	public double rule() {
		try {
			if( myCommandParser.parseCommands(params.get(0)) != 0 ){
				try {
					return myCommandParser.parseCommands(params.get(1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			else{
				try {
					return myCommandParser.parseCommands(params.get(2));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
