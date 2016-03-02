
package model.action.HigherOrderCommands;

import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Variable;

import model.TurtlePlayground;
import model.UserCommands;
import model.Variables;
import model.action.Action;

public class Make extends ControlStructures {

	private String var;
	private Double exp;

	public Make (List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
		super(params, language, playground, variables, usercommands);
		this.var = params.get(0);
		System.out.println(var);
		System.out.println(params.get(1));
		try {
			this.exp = myCommandParser.parseCommands(params.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public double rule() {		
		myCommandParser.getVariableList().addVariable(var, exp);
		return exp;
		
	}

}
