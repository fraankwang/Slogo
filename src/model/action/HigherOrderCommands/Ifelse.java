//package model.action.HigherOrderCommands;
//
//import java.util.List;
//
//import model.TurtlePlayground;
//import model.UserCommands;
//import model.Variables;
//
//public class Ifelse extends ControlStructures {
//
//	public Ifelse(List<String> params, String language, TurtlePlayground playground, Variables variables, UserCommands usercommands) {
//		super(params, language, playground, variables, usercommands);
//	}
//
//	@Override
//	public double rule() {
//		if( Double.parseDouble(params.get(0)) != 0 ){
//			return myCommandParser.parseCommands(params.get(1)); 
//		}
//		else{
//			return myCommandParser.parseCommands(params.get(2)); 
//		}
//	}
//
//}