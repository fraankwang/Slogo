/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class TurtleElement extends PanelElement {
	
	public TurtleElement(Node node, String name) {
		super(node, name);
		
	}

	public void setTurtleImage(ImageView imageView) {
		myNode = imageView;
	}
	
	public void moveTurtleImage(double x, double y) {
		myNode.setTranslateX(x);
		myNode.setTranslateY(y);
	}
	
	/**
	 * Rotates Image of turtle, offset to adjust for model
	 * @param degrees
	 */
	public void setTurtleOrientation(double degrees) {
		myNode.setRotate(270 - degrees);
	}
	
	
}
