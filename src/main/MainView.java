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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import constants.Constants;

public class MainView {

	private final int SCENE_WIDTH = 800;
	private final int SCENE_HEIGHT = 800;

	private Stage myPrimaryStage;
	private MainController myController;
	private Group myPrimaryRoot;
	private BorderPane myPrimaryPane;
	private Node myOutputBox;
	private Node myHistoryBox;
	private Node myTurtleBox;

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

		/**
		 * createPaneElements and place them in myPrimaryPane 1. TurtleBox can
		 * be a StackPane - this way we can overlap turtle drawings on the
		 * background 2. We should use a series of VBoxes and HBoxes (maybe
		 * GridPane to display myVariables). Toolbar at top of scene could be
		 * a HBox of buttons/ComboBoxes that allow us to pick configurations
		 */

		TextField tf = makeTextField();
		VBox rightColumn = new VBox();
		rightColumn.getChildren().add(tf);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;
	}

	/**
	 * @return TextField that sets myController to fully run the command and
	 *         refresh the display
	 */
	private TextField makeTextField() {
		TextField textField = new TextField(Constants.getSpecification("TextFieldDefault"));

		textField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myController.executeCommand(textField.getText());
				textField.clear();
			}
		});

		return textField;

	}

	/**
	 * Takes @param element and places it at @param position within the
	 * primaryPane
	 */
	private void placePaneElement(Node element, String position) {
		// figure out Position stuff, otherwise just position element inside
		// initializeRoot()
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

}
