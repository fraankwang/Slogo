/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PanelElementFactory {

	private static final int LEFT_COLUMN_WIDTH = Constants.LEFT_COLUMN_WIDTH;
	private static final int PLAYGROUND_HEIGHT = Constants.PLAYGROUND_HEIGHT;
	private static final int RIGHT_COLUMN_WIDTH = Constants.RIGHT_COLUMN_WIDTH;
	private static final int RIGHT_COLUMN_ELEMENT_HEIGHT = Constants.RIGHT_COLUMN_ELEMENT_HEIGHT;

	private MainController myController;

	private GraphicsContext myTurtleGraphics;
	private TurtleBackground myTurtleBackground;
	private VariablesElement variablesElement;
	private CommandsElement commandsElement;
	private HistoryElement historyElement;
	private OutputElement outputElement;
	
	
	public PanelElementFactory(MainController controller) {
		myController = controller;

	}

	/**
	 * @return fully formatted and populated left column which includes
	 *         TurtleBackground PanelElement and input area
	 */
	public VBox createLeftColumn() {
		VBox leftColumn = new VBox();
		TurtleBackground turtleBackground = (TurtleBackground) createTurtleBackground();
		HBox inputBox = makeInputWrapper();
		leftColumn.getChildren().addAll(turtleBackground.getNode(), inputBox);

		return leftColumn;
	}

	/**
	 * @return fully formatted and populated right column which includes
	 *         PanelElements Variables, Commands, History, and output area
	 */
	public VBox createRightColumn() {
		VBox rightColumn = new VBox();

		VariablesElement variables = (VariablesElement) createVariablesElement();
		CommandsElement commands = (CommandsElement) createCommandsElement();
		HistoryElement history = (HistoryElement) createHistoryElement();
		OutputElement outputArea = (OutputElement) createOutputElement();

		rightColumn.getChildren().addAll(variables.getNode(), commands.getNode(), history.getNode(),
				outputArea.getNode());
		return rightColumn;
	}

	/**
	 * Wrapper element to put in left column
	 * @return
	 */
	private HBox makeInputWrapper() {
		HBox inputWrapper = new HBox();
		TextArea textArea = makeTextArea();

		VBox buttons = new VBox();
		buttons.getChildren().addAll(makeRunButton(textArea), makeClearButton(textArea));

		inputWrapper.getChildren().addAll(textArea, buttons);
		return inputWrapper;
	}

	/**
	 * @return TextArea that sets myController to fully run the command and
	 *         refresh the display
	 */
	private TextArea makeTextArea() {
		TextArea textArea = new TextArea();
		textArea.setPromptText(Constants.getSpecification("TextAreaDefaultText"));
		textArea.setPrefHeight(Constants.TEXTAREA_HEIGHT);
		return textArea;
	}

	/**
	 * Helper function to execute commands given in @param ta
	 * @return button that is set on action to call MainController
	 */
	private Button makeRunButton(TextArea ta) {
		Button runButton = new Button(Constants.getSpecification("RunButtonDefaultText"));
		runButton.setPrefHeight(Constants.RUN_BUTTON_HEIGHT);
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myController.executeCommand(ta.getText());
			}
		});
		return runButton;

	}

	/**
	 * Helper function to clear commands given in @param ta
	 * @return button that is set on action to clear the input box
	 */
	private Button makeClearButton(TextArea ta) {
		Button clearButton = new Button(Constants.getSpecification("ClearButtonDefaultText"));
		clearButton.setPrefHeight(Constants.CLEAR_BUTTON_HEIGHT);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ta.clear();
			}
		});
		return clearButton;

	}

	/**
	 * @return formatted TurtleBackground which initializes myTurtleGraphics
	 */
	public PanelElement createTurtleBackground() {

		StackPane myTurtleWrapper = new StackPane();
		myTurtleWrapper.setPrefHeight(PLAYGROUND_HEIGHT);
		myTurtleWrapper.setPrefWidth(LEFT_COLUMN_WIDTH);
		myTurtleWrapper.setBackground(
				new Background(new BackgroundFill(Constants.DEFAULT_BACKGROUND_COLOR, Constants.CORNER_RADIUS, null)));
		Canvas playground = new Canvas(LEFT_COLUMN_WIDTH, PLAYGROUND_HEIGHT);

		myTurtleWrapper.getChildren().add(playground);
		myTurtleBackground = new TurtleBackground(myTurtleWrapper,
				Constants.getSpecification("TurtleBackgroundElementName"));
		myTurtleBackground.setGraphics(playground.getGraphicsContext2D());
		
		return myTurtleBackground;

	}

	/**
	 * @return formatted VariablesElement
	 */
	public PanelElement createVariablesElement() {
		VBox variablesWrapper = new VBox();
		Text variablesLabel = new Text(Constants.getSpecification("VariablesLabel"));
		ObservableList<String> test = FXCollections.observableArrayList("variable0", "variable1");
		ListView<String> variablesListView = new ListView<String>(test);
		variablesListView.setEditable(true);
		variablesListView.setPrefSize(RIGHT_COLUMN_WIDTH, RIGHT_COLUMN_ELEMENT_HEIGHT);
		variablesListView.setCellFactory(TextFieldListCell.forListView());

		variablesWrapper.getChildren().addAll(variablesLabel, variablesListView);
		
		variablesElement = new VariablesElement(variablesWrapper,
				Constants.getSpecification("VariablesElementName"));
		variablesElement.setListView(variablesListView);
		return variablesElement;
	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createCommandsElement() {
		VBox commandsWrapper = new VBox();
		Text commandsLabel = new Text(Constants.getSpecification("CommandsLabel"));
		ObservableList<String> test = FXCollections.observableArrayList("command0", "command1");
		ListView<String> commandsListView = new ListView<String>(test);
		commandsListView.setPrefSize(RIGHT_COLUMN_WIDTH, RIGHT_COLUMN_ELEMENT_HEIGHT);
		commandsListView.setCellFactory(TextFieldListCell.forListView());

		commandsWrapper.getChildren().addAll(commandsLabel, commandsListView);
		
		commandsElement = new CommandsElement(commandsWrapper,
				Constants.getSpecification("CommandsElementName"));
		commandsElement.setListView(commandsListView);
		return commandsElement;
	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createHistoryElement() {
		VBox historyWrapper = new VBox();
		Text historyLabel = new Text(Constants.getSpecification("HistoryLabel"));
		ObservableList<String> test = FXCollections.observableArrayList("history0", "history1");
		ListView<String> historyListView = new ListView<String>(test);
		historyListView.setPrefSize(RIGHT_COLUMN_WIDTH, RIGHT_COLUMN_ELEMENT_HEIGHT);
		historyListView.setCellFactory(TextFieldListCell.forListView());

		historyWrapper.getChildren().addAll(historyLabel, historyListView);

		historyElement = new HistoryElement(historyWrapper,
				Constants.getSpecification("HistoryElementName"));
		historyElement.setListView(historyListView);
		return historyElement;
	}

	
	/**
	 * @return formatted OutputElement which has a set TextArea
	 */
	public PanelElement createOutputElement() {
		StackPane outputWrapper = new StackPane();
		outputWrapper.setPrefHeight(RIGHT_COLUMN_ELEMENT_HEIGHT);
		outputWrapper.setPrefWidth(RIGHT_COLUMN_WIDTH);
		outputWrapper.setBackground(new Background(new BackgroundFill(Color.WHITE, Constants.CORNER_RADIUS, null)));

		TextArea outputArea = new TextArea();
		outputWrapper.getChildren().add(outputArea);
		StackPane.setAlignment(outputArea, Pos.BOTTOM_LEFT);
		
		outputElement = new OutputElement(outputWrapper, Constants.getSpecification("OutputElementName"));
		outputElement.setTextArea(outputArea);
		
		return outputElement;
	}
	

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public GraphicsContext getTurtleGraphics() {
		return myTurtleBackground.getGraphics();
	}

	public TurtleBackground getTurtleBackground() {
		return myTurtleBackground;
	}

	public VariablesElement getVariablesElement() {
		return variablesElement;
	}

	public CommandsElement getCommandsElement() {
		return commandsElement;
	}

	public HistoryElement getHistoryElement() {
		return historyElement;
	}

	public OutputElement getOutputElement() {
		return outputElement;
	}

}
