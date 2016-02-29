/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class VariablesElement extends PanelElement {

	private ListView<String> myListView;
	
	public VariablesElement(Node node, String name) {
		super(node, name);
		
	}

	public void setListView(ListView<String> variablesListView) {
		myListView = variablesListView;
		
	}
	
	public ListView<String> getListView(){
		return myListView;
	}

}
