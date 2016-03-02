/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class TurtleBackground extends PanelElement {

	private GraphicsContext myTurtleGraphics;
	
	public TurtleBackground(Node node, String name) {
		super(node, name);
		
	}

	public void setGraphics(GraphicsContext graphicsContext) {
		myTurtleGraphics = graphicsContext;
		
	}

	public GraphicsContext getGraphics() {
		return myTurtleGraphics;
	}

//	public void setTurtleElement(TurtleElement turtleElement) {
//		myTurtleElement = turtleElement;
//		
//	}
//
//	public TurtleElement getTurtleElement() {
//		return myTurtleElement;
//	}
}
