//package model.action.HigherOrderCommands;
//
//import model.action.Action;
//
//public class Dotimes extends Action {
//
//	private String var;
//	private double limit;
//	private Action command;
//
//	public Dotimes ( String var, double limit, Action command) {
//		super();
//		this.var = var;
//		this.limit = limit;
//		this.command = command;
//
//	}
//
//	@Override
//	public double rule() {
//		variables.addVariable(var, 1);
//		double val = 0;
//		for ( int i = 0; i <limit; i++ ){
//			variables.addVariable(var, variables.getVariableValue(var) + 1 );
//			val = command.rule();
//		}
//		return val;
//
//	}
//
//}
