/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.Panel;
import view.Toolbar;


public class MainView {

	private final int SCENE_WIDTH = 1000;
	private final int SCENE_HEIGHT = 1000;

	private Stage myPrimaryStage;
	private MainController myController;
	private Group myPrimaryRoot;
	private BorderPane myPrimaryPane;
	private Node myOutputBox;
	private Node myHistoryBox;
	private Node myTurtleBox;
	private Node myCommandsBox;
	private Node myVariablesBox;

	public MainView(Stage stage) {
		initializeRoot();
		myPrimaryStage = stage;

	}

	/**
	 * Displays primaryRoot on the primaryStage
	 */
	public void display() {
		Scene scene = new Scene(myPrimaryRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		myPrimaryStage.setScene(scene);
		myPrimaryStage.show();

	}

	/**
	 * Primary init method which pieces together all the elements to be
	 * displayed
	 */
	private void initializeRoot() {
		Group root = new Group();
		myPrimaryPane = new BorderPane();
		root.getChildren().add(myPrimaryPane);

		Toolbar tb = new Toolbar(myController);
		Panel panel = new Panel(myController);
		
		HBox toolBar = tb.createToolBar();
		VBox leftColumn = panel.createLeftColumn();
		VBox rightColumn = panel.createRightColumn();

		myPrimaryPane.setTop(toolBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public void setController(MainController controller) {
		myController = controller;
	}

	public Node getMyOutputBox() {
		return myOutputBox;
	}

	public Node getMyHistoryBox() {
		return myHistoryBox;
	}

	public Node getMyTurtleBox() {
		return myTurtleBox;
	}

	public Node getMyCommandsBox() {
		return myCommandsBox;
	}

	public Node getMyVariablesBox() {
		return myVariablesBox;
	}

}
