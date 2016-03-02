/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class VariablesElement extends PanelElement {

	private ListView<String> myNamesListView;
	private ListView<String> myValuesListView;
	
	public VariablesElement(Node node, String name) {
		super(node, name);
	}

	public ListView<String> getNamesListView() {
		return myNamesListView;
	}

	public void setNamesListView(ListView<String> namesListView) {
		this.myNamesListView = namesListView;
	}

	public ListView<String> getValuesListView() {
		return myValuesListView;
	}

	public void setValuesListView(ListView<String> valuesListView) {
		this.myValuesListView = valuesListView;
	}


}
