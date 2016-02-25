package view;

import java.util.Arrays;
import java.util.List;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Panel {

	private MainController myController;

	private static final int LEFT_COLUMN_WIDTH = Constants.LEFT_COLUMN_WIDTH;
	private static final int PLAYGROUND_HEIGHT = Constants.PLAYGROUND_HEIGHT;
	private static final int RIGHT_COLUMN_WIDTH = Constants.RIGHT_COLUMN_WIDTH;
	private static final int RIGHT_COLUMN_ELEMENT_HEIGHT = Constants.RIGHT_COLUMN_ELEMENT_HEIGHT;

	private static final double ELEMENT_INSET_HORIZONTAL = Constants.ELEMENT_INSET_HORIZONTAL;
	private static final double ELEMENT_INSET_VERTICAL = Constants.ELEMENT_INSET_VERTICAL;
	
	private StackPane myTurtleBackground;
	private GraphicsContext turtleBox;

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

		/**
		 * NOTES ON THIS: 
		 * 1. Outer most wrapper is a VBox with 4 elements: Variables, Commands, History, Output Console 
		 * 2. Each of the first 3 elements should be scrollpanes, last element can just be a Text 
		 * 	2a. Scrollpanes have a setContent(Node) method, so you have to have another wrapper inside each scrollpane 
		 * 		NOTE: I think a GridPane would be good, and you can do some CSS formatting to make it neat. It would
		 * 		work well with the layout for picking variables/commands
		 * 	2b. For formatting purposes, you might want to make OutputConsole a stackpane with a text as a child 
		 * 3. Make sure to use VBox.setMargins (look at Toolbar.java lines 106-112 for reference) for the 4 elements to make them look neat
		 * 4. Whatever your element is that you're actually modifying (i.e. Map or ArrayList), make it a global
		 * variable and add getters/setters, then link them to myController, so ModelTransformer has access to them
		 * 	4a. If that doesn't make sense, look at myTurtleBox (GraphicsContext) and how it's traced
		 */

		VBox vb = new VBox();
		ScrollPane variablesScrollPane = createVariablesScrollPane();
		ScrollPane commandsScrollPane = createCommandsScrollPane();
		ScrollPane historyScrollPane = createHistoryScrollPane();
		StackPane outputPane = createOutputPane();
		
		List<Node> allElements = Arrays.asList(variablesScrollPane, commandsScrollPane, historyScrollPane, outputPane);
		setMargins(allElements);
		
		// TODO: change all print statements to show in output box instead of Eclipse console
		vb.getChildren().addAll(allElements);
		return vb;
	}

	/**
	 * @return formatted ScrollPane wrapper that contains StackPane with Variables elements
	 */
	private ScrollPane createVariablesScrollPane() {
		ScrollPane variablesScrollPane = new ScrollPane();
		
		variablesScrollPane.setPrefHeight(RIGHT_COLUMN_ELEMENT_HEIGHT);
		variablesScrollPane.setPrefWidth(RIGHT_COLUMN_WIDTH);
	
		StackPane variablesStackPane = new StackPane();
		variablesStackPane.setMaxWidth(RIGHT_COLUMN_WIDTH);
		
		Text txtbox = new Text();
		txtbox.setText("VARIABLES HERE");
		txtbox.setFill(Color.WHITE);
		txtbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (txtbox.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("modified variables text");
				}
	
			}
		});
	
		Rectangle rec = new Rectangle(RIGHT_COLUMN_ELEMENT_HEIGHT, 400);
		rec.setFill(Color.BLACK);
		rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (rec.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("pressed variables box");
				}
	
			}
	
		});
		
		variablesStackPane.getChildren().addAll(rec,txtbox);
		
		variablesScrollPane.setContent(variablesStackPane);
		return variablesScrollPane;
	}

	/**
	 * @return formatted ScrollPane wrapper that contains StackPane with commands elements
	 */
	private ScrollPane createCommandsScrollPane() {
		ScrollPane commandsScrollPane = new ScrollPane();
		
		commandsScrollPane.setPrefHeight(RIGHT_COLUMN_ELEMENT_HEIGHT);
		commandsScrollPane.setPrefWidth(RIGHT_COLUMN_WIDTH);

		StackPane commandsStackPane = new StackPane();
		commandsStackPane.setMaxWidth(RIGHT_COLUMN_WIDTH);
		
		Text txtbox = new Text();
		txtbox.setText("COMMANDS HERE");
		txtbox.setFill(Color.WHITE);
		txtbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (txtbox.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("modified commands text");
				}

			}
		});

		Rectangle rec = new Rectangle(RIGHT_COLUMN_ELEMENT_HEIGHT, 400);
		rec.setFill(Color.BLACK);
		rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (rec.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("pressed commands box");
				}

			}

		});
		
		commandsStackPane.getChildren().addAll(rec,txtbox);
		
		commandsScrollPane.setContent(commandsStackPane);
		return commandsScrollPane;
	}

	/**
	 * @return formatted ScrollPane wrapper that contains StackPane with History elements
	 */
	private ScrollPane createHistoryScrollPane() {
		ScrollPane historyScrollPane = new ScrollPane();
		
		historyScrollPane.setPrefHeight(RIGHT_COLUMN_ELEMENT_HEIGHT);
		historyScrollPane.setPrefWidth(RIGHT_COLUMN_WIDTH);
	
		StackPane historyStackPane = new StackPane();
		historyStackPane.setMaxWidth(RIGHT_COLUMN_WIDTH);
		
		Text txtbox = new Text();
		txtbox.setText("HISTORY HERE");
		txtbox.setFill(Color.WHITE);
		txtbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (txtbox.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("modified history text");
				}
	
			}
		});
	
		Rectangle rec = new Rectangle(RIGHT_COLUMN_ELEMENT_HEIGHT, 400);
		rec.setFill(Color.BLACK);
		rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (rec.getLayoutBounds().contains(event.getX(), event.getY())) {
					System.out.println("pressed history box");
				}
	
			}
	
		});
		
		historyStackPane.getChildren().addAll(rec,txtbox);
		
		historyScrollPane.setContent(historyStackPane);
		return historyScrollPane;
	}

	/**
	 * @return formatted StackPane wrapper that contains output elements
	 */
	private StackPane createOutputPane() {
		StackPane outputPane = new StackPane();
		outputPane.setPrefHeight(RIGHT_COLUMN_ELEMENT_HEIGHT);
		outputPane.setPrefWidth(RIGHT_COLUMN_WIDTH);
		outputPane.setBackground(new Background(new BackgroundFill(Color.BLACK, Constants.CORNER_RADIUS, null)));
		
		Text txt = new Text("Output Console");
		txt.setFill(Color.WHITE);
		StackPane.setAlignment(txt, Pos.BOTTOM_LEFT);
		outputPane.getChildren().add(txt);
		
		
		return outputPane;
	}

	/**
	 * Takes @param items, checks what type of Object they are, and applies relevant insets
	 */
	private void setMargins(List<Node> items) {
		Insets insets = new Insets(ELEMENT_INSET_HORIZONTAL, ELEMENT_INSET_VERTICAL,
				ELEMENT_INSET_HORIZONTAL, ELEMENT_INSET_VERTICAL);
		
		for (Node item: items) {
			VBox.setMargin(item, insets);
		}
		
	}
	
	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted VBox
	 */
	public VBox createLeftColumn() {
		VBox vb = new VBox();

		StackPane turtlePlayground = makePlayground();
		Group inputBox = makeInputBox();
		// ***this is just here for backend to test commands
		TextField tf = makeTextField();
		// ***this is just here for backend to test commands

		List<Node> allElements = Arrays.asList(turtlePlayground, inputBox);
		setMargins(allElements);
		vb.getChildren().addAll(turtlePlayground, inputBox, tf);
		return vb;
	}

	/**
	 * @return formatted inputBox
	 */
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

	/**
	 * @return formatted StackPane which contains turtle Canvas
	 */
	private StackPane makePlayground() {
		myTurtleBackground = new StackPane();
		myTurtleBackground.setPrefHeight(PLAYGROUND_HEIGHT);
		myTurtleBackground.setPrefWidth(LEFT_COLUMN_WIDTH);
		myTurtleBackground.setBackground(
				new Background(new BackgroundFill(Constants.DEFAULT_BACKGROUND_COLOR, Constants.CORNER_RADIUS, null)));
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

	// =========================================================================
	// Getters and Setters
	// =========================================================================	
	
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
