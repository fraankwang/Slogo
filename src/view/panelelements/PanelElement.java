// This entire file is part of my masterpiece.
// Sam Toffler
// I just to refactor this class and others because I think the creation of
// panel elements is the weakest part of the front end design. Code seems duplicated
// and not robust or extensible so I have attempted to fix that. I should note that
// I did not originally write this super class, but worked extensively with its sub
// class where I came across many of PanelElement's original pitfalls.

/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import view.IViewable;

public class PanelElement implements IViewable {

	protected Node myNode;
	private String myName;
	private VBox myWrappper;
	private Label myLabel;

	public PanelElement(Node node, String name) {
		myNode = node;
		myName = name;
		
	}
	
	/**
	 * Toggles visibility of Node within PanelElement
	 */
	@Override
	public void toggleDisplay() {
		if (myNode.isVisible()) {
			myNode.setVisible(false);
		} else {
			myNode.setVisible(true);
		}
	}

	@Override
	public String getName() {
		return myName;
	}
	
	public Node getNode() {
		return myNode;
	}

	public void setBackground(Background value) {
		if (myNode instanceof StackPane) {
			((StackPane) myNode).setBackground(value);
		}
	}


}
