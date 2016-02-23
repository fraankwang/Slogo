/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import constants.Constants;

public class MainView {
	
	private final int SCENE_WIDTH = 800;
	private final int SCENE_HEIGHT = 800;
	
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
		
		
	}

	public void display () {
		Scene scene = new Scene(myPrimaryRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		myPrimaryStage.setScene(scene);
		myPrimaryStage.show();
		
	}

	private void initializeRoot() {
		Group root = new Group();
		myPrimaryPane = new Pane();
		root.getChildren().add(myPrimaryPane);
		
		//createPaneElements and place them in myPrimaryPane
		
		
		//TextField needs to be wrapped in some UI thing that can be aligned around					
		TextField tf = makeTextField();
		myPrimaryPane.getChildren().add(tf);
		
		myPrimaryRoot = root;
	}
	
	
	private TextField makeTextField () {
		TextField textField = new TextField(Constants.getSpecification("TextFieldDefault"));
		
		textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override      
            public void handle (ActionEvent event) {       
                myController.runCommand(textField.getText());
                textField.clear();
            }      
        });
		
		return textField;
		
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
