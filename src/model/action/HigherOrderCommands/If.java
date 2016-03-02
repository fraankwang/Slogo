package model.action.HigherOrderCommands;

import java.util.List;

import model.UserCommands;
import model.Variables;
import model.action.Action;
import model.turtle.TurtlePlayground;

public class If extends ControlStructures{

	public If(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);

	}

	@Override
	public double rule() {
		try {
			if( myCommandParser.parseCommands(params.get(0)) != 0 ){
				System.out.println("run "+params.get(1));
				try {
					return myCommandParser.parseCommands(params.get(1));
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
