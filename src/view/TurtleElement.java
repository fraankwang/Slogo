/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class TurtleElement extends PanelElement {
	
	public TurtleElement(Node node, String name) {
		super(node, name);
		
	}

	public void setTurtleImage(ImageView imageView) {
		myNode = imageView;
	}
	
	public void rotateTurtleImage(double degrees) {
		myNode.setRotate(myNode.getRotate() + degrees);
	}
	
	public void setTurtleOrientation(double degrees) {
		myNode.setRotate(degrees);
	}
	
	
}
