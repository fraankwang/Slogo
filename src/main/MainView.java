/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import constants.Constants;
import constants.StringImageCell;

public class MainView {

	private final int SCENE_WIDTH = 1000;
	private final int SCENE_HEIGHT = 1000;

	private final int TOOLBAR_HEIGHT = 50;
	private final int TOOLBAR_INSET_HORIZONTAL = 10;
	private final int TOOLBAR_INSET_HORIZONTAL_LABEL = 15;
	private final int TOOLBAR_INSET_VERTICAL = 5;
	private final int TOOLBAR_LABEL_FONT_SIZE = 13;

	private List<String> TURTLE_IMAGES = Arrays.asList("basic", "duvall");

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

		HBox toolBar = createToolBar();
		VBox leftColumn = createLeftColumn();
		VBox rightColumn = createRightColumn();

		myPrimaryPane.setTop(toolBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;
	}

	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted HBox
	 */
	private HBox createToolBar() {
		HBox toolbar = new HBox();
		toolbar.setPrefHeight(TOOLBAR_HEIGHT);
		toolbar.setPrefWidth(SCENE_WIDTH);
		toolbar.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		Label penColorPickerLabel = makeLabel("PenColorPickerLabel");
		ColorPicker penColorPicker = new ColorPicker(Color.BLACK);
		penColorPicker.setOnAction(e -> myController.setPenColor(penColorPicker.getValue()));

		Label backgroundColorPickerLabel = makeLabel("BackgroundPickerLabel");
		ColorPicker backgroundColorPicker = new ColorPicker(Color.BLACK);
		backgroundColorPicker.setOnAction(e -> myController.setBackgroundColor(backgroundColorPicker.getValue()));

		Label imageChooserLabel = makeLabel("ImagePickerLabel");
		ComboBox<String> turtleImageChooser = makeComboBox(TURTLE_IMAGES);
		turtleImageChooser.setOnAction(e -> myController.setTurtleImage(turtleImageChooser.getValue()));

		// formatting
		HBox.setMargin(penColorPickerLabel, new Insets(TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));
		HBox.setMargin(penColorPicker, new Insets(TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));
		HBox.setMargin(backgroundColorPickerLabel, new Insets(TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));
		HBox.setMargin(backgroundColorPicker, new Insets(TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));
		HBox.setMargin(imageChooserLabel, new Insets(TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));
		HBox.setMargin(turtleImageChooser, new Insets(TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL));

		toolbar.getChildren().addAll(penColorPickerLabel, penColorPicker, backgroundColorPickerLabel,
				backgroundColorPicker, imageChooserLabel, turtleImageChooser);
		return toolbar;

		// // setting animation speed
		// Slider slider = new Slider(0, 100.0, 1.0);
		// slider.valueProperty().addListener(e ->
		// myController.setAnimationSpeed(slider.getValue()));

	}

	private ComboBox<String> makeComboBox(List<String> images) {
		ComboBox<String> turtleImageChooser = new ComboBox<String>();
		turtleImageChooser.getItems().addAll("basic", "duvall");
		turtleImageChooser.setCellFactory(listview -> new StringImageCell());
		turtleImageChooser.setButtonCell(new StringImageCell());

		return turtleImageChooser;

	}

	private Label makeLabel(String title) {
		Label label = new Label();
		label.setText(Constants.getSpecification(title));
		label.setTextFill(Color.WHITE);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setFont(new Font(TOOLBAR_LABEL_FONT_SIZE));
		return label;

	}

	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted VBox
	 */
	private VBox createLeftColumn() {
		VBox vb = new VBox();

		// ***this is just here for backend to test commands
		TextField tf = makeTextField();
		vb.getChildren().add(tf);
		// ***this is just here for backend to test commands

		Canvas turtlePlayground = makePlayground();
		Group inputBox = makeInputBox();

		vb.getChildren().addAll(turtlePlayground, inputBox);
		return vb;
	}

	private Group makeInputBox() {
		Group inputBox = new Group();
		// make border pane within this? set dimensions -- CALCULATE THIS using
		// SCENE_HEIGHT/WIDTH
		// some sort of scrollpane with textfield within it -- inputConsole
		// run button (myController.executeCommand(inputConsole.getText() -- or
		// something like this)
		// reference makeTextField code- create new Eventhandler and call
		// inputConsole.clear()
		// clear button - inputConsole.clear()

		return inputBox;
	}

	private Canvas makePlayground() {
		Canvas playground = new Canvas();

		// figure out turtle stuff, set dimensions

		return playground;
	}

	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted VBox
	 */
	private VBox createRightColumn() {
		// another borderpane? set dimensions -- CALCULATE THIS using
		// SCENE_HEIGHT/WIDTH
		// history/commands/variables should all be scrollpanes, outputBox can
		// just be a textbox or whatever
		// use VBox.setMargins(leftInset, topInset, rightInset, bottomInset) and
		// you should define insets as
		// global variables somewhere

		VBox vb = new VBox();

		return vb;
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
