/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.panelelements;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TurtleInfoElement extends PanelElement {

//	private ListView<String> myListView;
	private TabPane myTabPane;
	
	public TurtleInfoElement(Node node, String name) {
		super(node, name);
	}

//	public void setListView(ListView<String> commandsListView) {
//		myListView = commandsListView;
//		
//	}
//	
//	public ListView<String> getListView(){
//		return myListView;
//	}
	
	public void addTab(Tab tab){
		myTabPane.getTabs().add(tab);
	}
	
	public TabPane getTabPane(){
		return myTabPane;
	}
	
	public void setTabPane(TabPane tabPane){
		myTabPane = tabPane;
	}

}

