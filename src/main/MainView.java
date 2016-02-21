/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainView {
	
	private Stage myPrimaryStage;
	private MainController myController;
	private Group myPrimaryRoot;
	private Pane myPrimaryPane;
	private Node myOutputBox;
	private Node myHistoryBox;
	private Node myTurtleBox;
	
	
	public MainView (Stage stage) {
		initializeRoot();
		myPrimaryStage = stage;
		display();
		
	}

	public void display () {
		Scene scene = new Scene(myPrimaryRoot, 500, 500);
		myPrimaryStage.setScene(scene);
		myPrimaryStage.show();
		
	}

	private void initializeRoot() {
		Group root = new Group();
		myPrimaryPane = new Pane();
		root.getChildren().add(myPrimaryPane);
		
		//createPaneElements and place them in myPrimaryPane
		
		
							
		TextInputDialog inputBox = createInputBox();
		//can't add TextInputDialog to Pane??
		
		myPrimaryRoot = root;
	}
	
	
	
	private TextInputDialog createInputBox () {
		TextInputDialog inputBox = new TextInputDialog();
		
		return inputBox;
	}
	
	private void placePaneElement (Node element, String position) {
		// figure out Position stuff, otherwise just position element inside initializeRoot()
	}
	
	public void setController(MainController controller) {
		myController = controller;
		
	}

	public Node getMyOutputBox () {
		return myOutputBox;
	}

	public Node getMyHistoryBox () {
		return myHistoryBox;
	}

	public Node getMyTurtleBox () {
		return myTurtleBox;
	}

}
