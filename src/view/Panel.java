package view;

import constants.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.MainController;

public class Panel {

	MainController myController;
	
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
	
	
}
