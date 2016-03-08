/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.factories;

import java.util.List;
import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import view.panelelements.ColorsElement;
import view.panelelements.CommandsElement;
import view.panelelements.HistoryElement;
import view.panelelements.OutputElement;
import view.panelelements.PanelElement;
import view.panelelements.TurtleBackground;
import view.panelelements.TurtleElement;
import view.panelelements.VariablesElement;

public class PanelElementFactory {

	private static final double LEFT_COLUMN_WIDTH = Constants.LEFT_COLUMN_WIDTH;
	private static final double PLAYGROUND_HEIGHT = Constants.PLAYGROUND_HEIGHT;
	private static final double PLAYGROUND_WIDTH = Constants.PLAYGROUND_WIDTH;
	private static final double RIGHT_COLUMN_WIDTH = Constants.RIGHT_COLUMN_WIDTH;
	public static final double TEXTAREA_WIDTH = Constants.TEXTAREA_WIDTH;
	public static final double TEXTAREA_HEIGHT = Constants.TEXTAREA_HEIGHT;
	public static final double VARIABLES_WIDTH = Constants.VARIABLES_WIDTH;
	public static final double VARIABLES_HEIGHT = Constants.VARIABLES_HEIGHT;
	public static final double COLORS_WIDTH = Constants.COLORS_WIDTH;
	public static final double COLORS_HEIGHT = Constants.COLORS_HEIGHT;
	public static final double COMMANDS_WIDTH = Constants.COMMANDS_WIDTH;
	public static final double COMMANDS_HEIGHT = Constants.COMMANDS_HEIGHT;
	public static final double HISTORY_WIDTH = Constants.HISTORY_WIDTH;
	public static final double HISTORY_HEIGHT = Constants.HISTORY_HEIGHT;
	
	public static final double OUTPUT_WIDTH = Constants.OUTPUT_WIDTH;
	public static final double OUTPUT_HEIGHT = Constants.OUTPUT_HEIGHT;
	//private static final double RIGHT_COLUMN_ELEMENT_HEIGHT = Constants.RIGHT_COLUMN_ELEMENT_HEIGHT;

	private static final double ELEMENT_INSET_HORIZONTAL = Constants.ELEMENT_INSET_HORIZONTAL;
	private static final double ELEMENT_INSET_VERTICAL = Constants.ELEMENT_INSET_VERTICAL;

	private MainController myController;

	private TextArea textArea;
	private Button runButton;
	private Button clearButton;
	private GraphicsContext myTurtleGraphics;
	private TurtleBackground myTurtleBackground;
	private Canvas myTurtlePlayground;
	private StackPane myTurtleWrapper;
	private TurtleElement myTurtleElement;
	private VariablesElement variablesElement;
	private CommandsElement commandsElement;
	private HistoryElement historyElement;
	private OutputElement outputElement;
	private ColorsElement colorsElement;

	public PanelElementFactory(MainController controller) {
		myController = controller;
	}

	/**
	 * @return fully formatted and populated left column which includes
	 *         TurtleBackground PanelElement and input area
	 */
	public VBox createLeftColumn() {
		VBox leftColumn = new VBox();

//		VBox turtleVBox = new VBox();
//		TurtleBackground turtleBackground = (TurtleBackground) createTurtleBackground();
//		turtleVBox.getChildren().add(turtleBackground.getNode());
//		turtleVBox.getStyleClass().add("turtle-vbox");
//
		VBox inputVBox = new VBox();
		HBox inputBox = makeInputWrapper();
		inputVBox.getChildren().add(inputBox);
//		inputVBox.getStyleClass().add("input-vbox");
//
//		leftColumn.getChildren().addAll(turtleVBox, inputVBox);
		leftColumn.getChildren().addAll(createTurtleBackground().getNode(), inputVBox);
		leftColumn.getStyleClass().add("left-column");
		return leftColumn;
	}

	/**
	 * @return fully formatted and populated right column which includes
	 *         PanelElements Variables, Commands, History, and output area
	 */
	public VBox createRightColumn() {
		VBox rightColumn = new VBox();
		
		HBox topBoxes = new HBox();
		HBox middleBoxes = new HBox();

		VariablesElement variables = (VariablesElement) createVariablesElement();
		CommandsElement commands = (CommandsElement) createCommandsElement();
		HistoryElement history = (HistoryElement) createHistoryElement();
		OutputElement outputArea = (OutputElement) createOutputElement();
		ColorsElement colors = (ColorsElement) createColorsElement();
		
		topBoxes.getChildren().addAll(variables.getNode(), colors.getNode());
		topBoxes.getStyleClass().add("top-boxes");
		middleBoxes.getChildren().addAll(commands.getNode(), history.getNode());
		middleBoxes.getStyleClass().add("middle-boxes");

//	    List<Node> allNodes = Arrays.asList(variables.getNode(),
//		commands.getNode(), history.getNode(),
//		outputArea.getNode());
//		setMargins(allNodes);
//		variables.getNode().getStyleClass().add("variables-element");
//		commands.getNode().getStyleClass().add("commands-element");
//		history.getNode().getStyleClass().add("history-element");
//		outputArea.getNode().getStyleClass().add("output-element");
//		colors.getNode().getStyleClass().add("color-element");
//
//		rightColumn.getChildren().addAll(variables.getNode(), commands.getNode(), history.getNode(),
//				outputArea.getNode(), colors.getNode());
		rightColumn.getChildren().addAll(topBoxes, middleBoxes, outputArea.getNode());
		rightColumn.getStyleClass().add("right-column");
		return rightColumn;

	}

	/**
	 * Formats @param items using pre-defined insets
	 */
	private void setMargins(List<Node> items) {
		Insets insets = new Insets(ELEMENT_INSET_HORIZONTAL, ELEMENT_INSET_VERTICAL, ELEMENT_INSET_HORIZONTAL,
				ELEMENT_INSET_VERTICAL);
		for (Node item : items) {
			VBox.setMargin(item, insets);
		}

	}

	/**
	 * Wrapper element to put in left column
	 * 
	 * @return
	 */
	private HBox makeInputWrapper() {
		
		HBox inputWrapper = new HBox();
		TextArea textArea = makeTextArea();

		VBox buttons = new VBox();
		buttons.getChildren().addAll(makeRunButton(), makeClearButton());

		inputWrapper.getChildren().addAll(textArea, buttons);
		return inputWrapper;
	}

	/**
	 * @return TextArea that sets myController to fully run the command and
	 *         refresh the display
	 */
	private TextArea makeTextArea() {
		textArea = new TextArea();
		textArea.setPromptText(Constants.getSpecification("TextAreaDefaultText"));
		textArea.setPrefSize(TEXTAREA_WIDTH, TEXTAREA_HEIGHT);
		return textArea;
	}

	/**
	 * Helper function to execute commands given in @param ta
	 * 
	 * @return button that is set on action to call MainController
	 */
	private Button makeRunButton() {
		runButton = new Button(Constants.getSpecification("RunButtonDefaultText"));
		runButton.setPrefSize(Constants.RUN_BUTTON_WIDTH, Constants.RUN_BUTTON_HEIGHT);

		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myController.executeCommand(textArea.getText());
			}
		});

		runButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCodeCombination hotkeyRun = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
				if (hotkeyRun.match(event)) {
					myController.executeCommand(textArea.getText());
				}

			}
		});
		return runButton;
	}
	
	/**
	 * Helper function to clear commands given in @param ta
	 * 
	 * @return button that is set on action to clear the input box
	 */
	private Button makeClearButton() {
		clearButton = new Button(Constants.getSpecification("ClearButtonDefaultText"));
		clearButton.setPrefSize(Constants.CLEAR_BUTTON_WIDTH, Constants.CLEAR_BUTTON_HEIGHT);

		clearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				textArea.clear();
			}
		});

		clearButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCodeCombination hotkeyClear = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
				if (hotkeyClear.match(event)) {
					textArea.clear();
				}

			}
		});
		return clearButton;
	}

	/**
	 * @return formatted TurtleBackground which initializes myTurtleGraphics
	 */
	public PanelElement createTurtleBackground() {
		myTurtleWrapper = new StackPane();
		myTurtleWrapper.setPrefHeight(PLAYGROUND_HEIGHT);
		myTurtleWrapper.setPrefWidth(LEFT_COLUMN_WIDTH);
		myTurtleWrapper.setBackground(
				new Background(new BackgroundFill(Constants.DEFAULT_BACKGROUND_COLOR, Constants.CORNER_RADIUS, null)));
		myTurtlePlayground = new Canvas(LEFT_COLUMN_WIDTH, PLAYGROUND_HEIGHT);

		myTurtleElement = createTurtleElement();
		myTurtleWrapper.getChildren().addAll(myTurtlePlayground, myTurtleElement.getNode());

		myTurtleBackground = new TurtleBackground(myTurtleWrapper,
				Constants.getSpecification("TurtleBackgroundElementName"));
		myTurtleBackground.setGraphics(myTurtlePlayground.getGraphicsContext2D());

		return myTurtleBackground;

	}

	/**
	 * Formatted TurtleElement
	 * 
	 * @return
	 */
	private TurtleElement createTurtleElement() {
		ImageView turtleImage = new ImageView(
				new Image(getClass().getClassLoader().getResourceAsStream(Constants.getDefaultTurtleImageFileName())));
		turtleImage.setFitWidth(Constants.TURTLE_ELEMENT_WIDTH);
		turtleImage.setFitHeight(Constants.TURTLE_ELEMENT_HEIGHT);

		TurtleElement turtleElement = new TurtleElement(turtleImage, Constants.getSpecification("TurtleElementName"));

		turtleElement.getNode().getStyleClass().add("turtle-element");
		return turtleElement;

	}

	/**
	 * @return formatted VariablesElement
	 */
	public PanelElement createVariablesElement() {
		VBox variablesWrapper = new VBox();
		Label variablesLabel = new Label(Constants.getSpecification("VariablesLabel"));
		HBox variablesListViews = new HBox();
		ListView<String> variablesNamesListView = createVariablesNamesListView();
		ListView<String> variablesValuesListView = createVariablesValuesListView(variablesNamesListView);

		variablesListViews.getChildren().addAll(variablesNamesListView, variablesValuesListView);

		variablesWrapper.getChildren().addAll(variablesLabel, variablesListViews);

		variablesElement = new VariablesElement(variablesWrapper, Constants.getSpecification("VariablesElementName"));
		variablesElement.setNamesListView(variablesNamesListView);
		variablesElement.setValuesListView(variablesValuesListView);
		return variablesElement;
	}

	/**
	 * Helper method to create an editable listview that links to variable keys
	 * 
	 * @param names
	 * @return
	 */
	private ListView<String> createVariablesNamesListView() {
		ListView<String> namesListView = new ListView<String>();
		namesListView.setPrefSize(VARIABLES_WIDTH / 2.0, VARIABLES_HEIGHT);

		namesListView.setCellFactory(TextFieldListCell.forListView());
		namesListView.setEditable(true);
		namesListView.setCellFactory(TextFieldListCell.forListView());
		namesListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				myController.replaceVariable(namesListView.getSelectionModel().getSelectedItem(), t.getNewValue());
				myController.refreshDisplay();
			}
		});
		// For later
		// variablesListView.setCellFactory(listview -> new SettingsCell());
		return namesListView;
	}
	

	/**
	 * Helper method to create an editable listview that links to variable
	 * values
	 * 
	 * @param names
	 * @return
	 */
	private ListView<String> createVariablesValuesListView(ListView<String> names) {
		ListView<String> valuesListView = new ListView<String>();
		valuesListView.setPrefSize(VARIABLES_WIDTH / 2.0, VARIABLES_HEIGHT);

		valuesListView.setCellFactory(TextFieldListCell.forListView());
		valuesListView.setEditable(true);
		valuesListView.setCellFactory(TextFieldListCell.forListView());
		valuesListView.setOnEditCommit(new EventHandler<ListView.EditEvent<String>>() {
			@Override
			public void handle(ListView.EditEvent<String> t) {
				int indexToChange = valuesListView.getSelectionModel().getSelectedIndex();
				names.getSelectionModel().select(indexToChange);
				myController.replaceVariableValue(names.getSelectionModel().getSelectedItem(), t.getNewValue());
				myController.refreshDisplay();
			}
		});
		return valuesListView;
	}
	
	private PanelElement createColorsElement() {
		VBox colorsWrapper = new VBox();
		Label colorsLabel = new Label(Constants.getSpecification("ColorsLabel"));
		ListView<String> colorsListView = new ListView<String>();
		colorsListView.setPrefSize(COLORS_WIDTH, COLORS_HEIGHT);
		colorsListView.setCellFactory(TextFieldListCell.forListView());
		
		colorsWrapper.getChildren().addAll(colorsLabel, colorsListView);
		
		colorsElement = new ColorsElement(colorsWrapper, Constants.getSpecification("ColorsElementName"));
		colorsElement.setListView(colorsListView);
		
		return colorsElement;
	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createCommandsElement() {
		VBox commandsWrapper = new VBox();
		Label commandsLabel = new Label(Constants.getSpecification("CommandsLabel"));
		ListView<String> commandsListView = new ListView<String>();
		commandsListView.setPrefSize(COMMANDS_WIDTH, COMMANDS_HEIGHT);
		commandsListView.setCellFactory(TextFieldListCell.forListView());

		commandsWrapper.getChildren().addAll(commandsLabel, commandsListView);

		commandsElement = new CommandsElement(commandsWrapper, Constants.getSpecification("CommandsElementName"));
		commandsElement.setListView(commandsListView);

		commandsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				textArea.setText(commandsListView.getSelectionModel().getSelectedItem());
				// myController.executeCommand(textArea.getText());
			}
		});

		return commandsElement;

	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createHistoryElement() {
		VBox historyWrapper = new VBox();
		Label historyLabel = new Label(Constants.getSpecification("HistoryLabel"));
		ListView<String> historyListView = new ListView<String>();
		historyListView.setPrefSize(HISTORY_WIDTH, HISTORY_HEIGHT);
		historyListView.setCellFactory(TextFieldListCell.forListView());

		historyListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!historyListView.getItems().isEmpty()) {
					String text = textArea.getText();
					if (text.length() > 1) {
						text = text + "\n" + historyListView.getSelectionModel().getSelectedItem();
					} else {
						text = text + historyListView.getSelectionModel().getSelectedItem();
					}

					textArea.setText(text);

				}
			}
		});
		historyWrapper.getChildren().addAll(historyLabel, historyListView);

		historyElement = new HistoryElement(historyWrapper, Constants.getSpecification("HistoryElementName"));
		historyElement.setListView(historyListView);
		return historyElement;

	}

	/**
	 * @return formatted OutputElement which has a set TextArea
	 */
	public PanelElement createOutputElement() {
		VBox outputWrapper = new VBox();
		Label outputLabel = new Label(Constants.getSpecification("OutputLabel"));
		outputLabel.setTextAlignment(TextAlignment.CENTER);
		outputWrapper.setPrefSize(OUTPUT_WIDTH, OUTPUT_HEIGHT);
		outputWrapper.setBackground(new Background(new BackgroundFill(Color.WHITE, Constants.CORNER_RADIUS, null)));

		TextArea outputArea = new TextArea();
		outputArea.setPrefSize(OUTPUT_WIDTH, OUTPUT_HEIGHT);
		outputArea.setEditable(false);
		outputWrapper.getChildren().addAll(outputLabel, outputArea);
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

	public TurtleElement getTurtleElement() {
		return myTurtleElement;
	}

	public StackPane getTurtleWrapper() {
		return myTurtleWrapper;
	}

	public Canvas getTurtlePlayground() {
		return myTurtlePlayground;
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
	
	public ColorsElement getColorsElement() {
		return colorsElement;
	}
	
	public TextArea getTextArea() {
		return textArea;
	}

}
