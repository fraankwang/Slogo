/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class PanelElement implements IViewable {

	protected Node myNode;
	private String myName;

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
