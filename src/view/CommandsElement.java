/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class CommandsElement extends PanelElement {

	private ListView<String> myListView;
	
	public CommandsElement(Node node, String name) {
		super(node, name);
	}

	public void setListView(ListView<String> commandsListView) {
		myListView = commandsListView;
		
	}

}