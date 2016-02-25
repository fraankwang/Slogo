package view;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Panel {

	private MainController myController;

	private final int SCENE_WIDTH = 1000;
	private final int SCENE_HEIGHT = 1000;
	private final int LEFT_COLUMN_WIDTH = 700;
	private final int PLAYGROUND_HEIGHT = 550;
	private final int RIGHT_COLUMN_WIDTH = 300;
	
	CornerRadii CORNER_RADIUS = new CornerRadii(10.0);
	StackPane myTurtleBackground;
	GraphicsContext turtleBox;
	
	public Panel(MainController controller) {
		myController = controller;

	}

	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted VBox
	 */
	public VBox createRightColumn() {
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
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted VBox
	 */
	public VBox createLeftColumn() {
		VBox vb = new VBox();

		// ***this is just here for backend to test commands
		TextField tf = makeTextField();
		// ***this is just here for backend to test commands

		StackPane turtlePlayground = makePlayground();
		Group inputBox = makeInputBox();

		vb.getChildren().addAll(turtlePlayground, inputBox, tf);
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

	private StackPane makePlayground() {
		myTurtleBackground = new StackPane();
		myTurtleBackground.setPrefHeight(PLAYGROUND_HEIGHT);
		myTurtleBackground.setPrefWidth(LEFT_COLUMN_WIDTH);

		Canvas playground = new Canvas(LEFT_COLUMN_WIDTH, PLAYGROUND_HEIGHT);
		setTurtleBox(playground.getGraphicsContext2D());

		myTurtleBackground.getChildren().add(playground);
		return myTurtleBackground;
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

	private void setTurtleBox(GraphicsContext tb) {
		turtleBox = tb;
	}
	
	public GraphicsContext getTurtleBox() {
		return turtleBox;
	}

	public StackPane getTurtleBackground() {
		return myTurtleBackground;
	}
}
