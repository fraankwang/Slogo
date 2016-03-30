package view.panelelements;

import java.util.ArrayList;

import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class TablePanelElement extends PanelElement {

	private ListView<String> myNamesListView;
	private ListView<String> myValuesListView;
	
	public TablePanelElement(String name) {
		super(name);
	}
	
	public void addTable(){
		myNamesListView = new ListView<String>();
		myValuesListView = new ListView<String>();
		myElement.getChildren().add(new HBox(myNamesListView, myValuesListView));
	}
	
	public void setTableNames(ArrayList<String> names){
		myNamesListView.getItems().clear();
		for(String s : names){
			myNamesListView.getItems().add(s);
		}
	}
	
	public void setTableValues(ArrayList<String> values){
		myValuesListView.getItems().clear();
		for(String s : values){
			myValuesListView.getItems().add(s);
		}
	}
	
	public void setNamesEditable(boolean editable){
		if(editable){
			myNamesListView.setEditable(true);
		}
	}
	
	public void setValuesEditable(boolean editable){
		if(editable){
			myValuesListView.setEditable(true);
		}
	}
}
