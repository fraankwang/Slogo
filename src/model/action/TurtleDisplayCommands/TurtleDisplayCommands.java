package model.action.TurtleDisplayCommands;

import model.Palette;

import model.action.Action;

public abstract class TurtleDisplayCommands extends Action{

	protected Palette palette;

	public TurtleDisplayCommands( Palette newPalette){
		super();
		palette= newPalette;
	}
	
	
}
