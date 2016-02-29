/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class OutputElement extends PanelElement {

	TextArea textArea;
	public OutputElement(Node node, String name) {
		super(node, name);
	}

	public void setTextArea(TextArea outputArea) {
		textArea = outputArea;
		
	}
	
	public TextArea getText(){
		return textArea;
	}

}
