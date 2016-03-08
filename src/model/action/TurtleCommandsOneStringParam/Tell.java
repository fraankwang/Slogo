package model.action.TurtleCommandsOneStringParam;

import java.util.Arrays;
import java.util.List;

import model.turtle.TurtlePlayground;

public class Tell extends TurtleCommandsOneStringParam {

	List<String> commands;

	public Tell(List<String> params, TurtlePlayground playground) {
		super(params, playground);
		commands = Arrays.asList(command.split(" "));
		System.out.println(commands.size() + "command size");
	}

	@Override
	public double rule() {
		for (String s : commands) {
			playground.getTurtle(Integer.parseInt(s));
			playground.getActiveTurtles().add(playground.getTurtle(Integer.parseInt(s)));
		}
		return Double.parseDouble(commands.get(commands.size() - 1));
	}

}
