/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleElement extends PanelElement {
	
	List<ImageView> stamps = new ArrayList<ImageView>();
	Image myImage;
	
	public TurtleElement(Node node, String name) {
		super(node, name);
		
	}

	public void setTurtleImage(ImageView imageView) {
		myImage = imageView.getImage();
		myNode = imageView;
	}
	
	public Image getImage(){
		return myImage;
	}
	
	public void moveTurtleImage(double x, double y) {
		myNode.setTranslateX(x);
		myNode.setTranslateY(y);
	}
	
	public void stamp(double x, double y) {
		Double currentWidth = ((ImageView) myNode).getFitWidth();
		Double currentHeight = ((ImageView) myNode).getFitHeight();
		Image currentImage = ((ImageView) myNode).getImage();
		ImageView stampNode = new ImageView(currentImage);
		stampNode.setFitWidth(currentWidth);
		stampNode.setFitHeight(currentHeight);
		stampNode.setTranslateX(x);
		stampNode.setTranslateY(y);
		stamps.add(stampNode);
	}
	
	public void clearStamps() {
		stamps.clear();
	}
	
	/**
	 * Rotates Image of turtle, offset to adjust for model
	 * @param degrees
	 */
	public void setTurtleOrientation(double degrees) {
		myNode.setRotate(degrees);
	}
	
	
}
