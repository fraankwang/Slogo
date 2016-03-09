/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class ColorsElement extends PanelElement {

	private ListView<String> myIntegersListView;
	private ListView<String> myValuesListView;
	
	public ColorsElement(Node node, String name) {
		super(node, name);
	}

	public void setIntegersListView(ListView<String> integersListView) {
		myIntegersListView = integersListView;
		
	}
	
	public ListView<String> getIntegersListView(){
		return myIntegersListView;
	}
	
	public void setValuesListView(ListView<String> valuesListView) {
		myValuesListView = valuesListView;
		
	}
	
	public ListView<String> getValuesListView(){
		return myValuesListView;
	}

}