/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import javafx.scene.Node;
import javafx.scene.control.ListView;

public class HistoryElement extends PanelElement {

	private ListView<String> myListView;
	
	public HistoryElement(Node node, String name) {
		super(node, name);
	}

	public void setListView(ListView<String> historyListView) {
		myListView = historyListView;
		
	}
	
	public ListView<String> getListView(){
		return myListView;
	}

}
