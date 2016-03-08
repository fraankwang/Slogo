/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;

public interface IViewable {
	public void toggleDisplay();
	public String getName();
	public Node getNode();
}
