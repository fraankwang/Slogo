/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class VariablesElement extends PanelElement {

	private ArrayList<ListView<String>> myListView = new ArrayList<ListView<String>>();
	
	public VariablesElement(Node node, String name) {
		super(node, name);
	}

	public void addListView(ListView<String> variablesListView) {
		myListView.add(variablesListView);
	}
	
	public ArrayList<ListView<String>> getListView() {
		return myListView;
	}

}
