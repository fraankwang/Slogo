package view.panelelements;

import java.util.ArrayList;

import javafx.scene.control.ListView;

public class ListViewPanelElement extends PanelElement{

	private ListView<String> myListView;
	
	public ListViewPanelElement(String name) {
		super(name);
	}

	public void addListView(){
		myListView = new ListView<String>();
		myElement.getChildren().add(myListView);
	}
	
	public void setListView(ArrayList<String> list){
		myListView.getItems().clear();
		for(String s : list){
			myListView.getItems().add(s);
		}
	}
	
	public void setEditable(boolean editable){
		if(editable){
			myListView.setEditable(true);
		}
	}
}
