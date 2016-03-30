// This entire file is part of my masterpiece.
// Sam Toffler
// I have attempted to re-factor the way in which our panel elements are created. In my
// opinion, I think that this is the weakest part of the front-end team's code. It is pretty dirty,
// and in my opinion there was lot of duplicated code. I think that I have made it more readable,
// more manageable, and more robust. Instead of creating a new panel element class for every single
// element that appears on the screen, I have tried to create a class for every *type* of panel
// that could appear. It is not perfected, but it covers all bases for our project. Also,
// if we wanted to make a new type of panel element we could easily create a new class. For the most
// part though, I have reduced seven classes to three classes.

/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

// getting an error because not implementing all methods that
// are required by the IViewable interface
public class PanelElement /*implements IViewable*/ {

	protected VBox myElement;
	private String myName;
	
	public PanelElement(String name) {
		myName = name;
		myElement = new VBox();
		myElement.getChildren().add(new Label(name));
	}
	
	public VBox getElement(){
		return myElement;
	}
}
