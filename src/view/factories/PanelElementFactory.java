/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.factories;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import view.panelelements.ColorsElement;
import view.panelelements.CommandsElement;
import view.panelelements.HistoryElement;
import view.panelelements.OutputElement;
import view.panelelements.PanelElement;
import view.panelelements.TurtleBackground;
import view.panelelements.TurtleElement;
import view.panelelements.TurtleInfoElement;
import view.panelelements.VariablesElement;

public class PanelElementFactory {

	private MainController myController;

	private TextArea myTextArea;
	private Button myRunButton;
	private Button myClearButton;
	private GraphicsContext myTurtleGraphics;
	private TurtleBackground myTurtleBackground;
	private Canvas myTurtlePlayground;
	private StackPane myTurtleWrapper;
	private TurtleElement myTurtleElement;
	private VariablesElement myVariablesElement;
	private CommandsElement myCommandsElement;
	private HistoryElement myHistoryElement;
	private OutputElement myOutputElement;
	private ColorsElement myColorsElement;
	private TurtleInfoElement myTurtleInfoElement;

	public PanelElementFactory(MainController controller) {
		myController = controller;
		createTurtleBackground();
		createVariablesElement();
		createCommandsElement();
		createHistoryElement();
		createOutputElement();
		createColorsElement();
		createTurtleInfoElement();
	}

	/**
	 * @return fully formatted and populated left column which includes
	 *         TurtleBackground PanelElement and input area
	 */
	public VBox createLeftColumn() {
		VBox leftColumn = new VBox();
		HBox inputBox = makeInputWrapper();
		leftColumn.getChildren().addAll(myTurtleBackground.getNode(), inputBox);
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
		HBox bottomBoxes = new HBox();

		topBoxes.getChildren().addAll(myVariablesElement.getNode(), myColorsElement.getNode());
		topBoxes.getStyleClass().add("top-boxes");
		middleBoxes.getChildren().addAll(myCommandsElement.getNode(), myHistoryElement.getNode());
		middleBoxes.getStyleClass().add("middle-boxes");
		bottomBoxes.getChildren().addAll(myTurtleInfoElement.getNode(), myOutputElement.getNode());
		bottomBoxes.getStyleClass().add("bottom-boxes");
		rightColumn.getChildren().addAll(topBoxes, middleBoxes, bottomBoxes);
		rightColumn.getStyleClass().add("right-column");
		return rightColumn;

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
		myTextArea = new TextArea();
		myTextArea.setPromptText(Constants.getSpecification("TextAreaDefaultText"));
		myTextArea.setPrefSize(Constants.TEXTAREA_WIDTH, Constants.TEXTAREA_HEIGHT);
		return myTextArea;
	}

	/**
	 * Helper function to execute commands given in @param ta
	 * 
	 * @return button that is set on action to call MainController
	 */
	private Button makeRunButton() {
		myRunButton = new Button(Constants.getSpecification("RunButtonDefaultText"));
		myRunButton.setPrefSize(Constants.RUN_BUTTON_WIDTH, Constants.RUN_BUTTON_HEIGHT);
		myRunButton.setStyle("-fx-font: 25 Helvetica");

		myRunButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myController.executeCommand(myTextArea.getText());
			}
		});

		myRunButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCodeCombination hotkeyRun = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
				if (hotkeyRun.match(event)) {
					myController.executeCommand(myTextArea.getText());
				}

			}
		});
		return myRunButton;

	}

	/**
	 * Helper function to clear commands given in @param ta
	 * 
	 * @return button that is set on action to clear the input box
	 */
	private Button makeClearButton() {
		myClearButton = new Button(Constants.getSpecification("ClearButtonDefaultText"));
		myClearButton.setPrefSize(Constants.CLEAR_BUTTON_WIDTH, Constants.CLEAR_BUTTON_HEIGHT);
		myClearButton.setStyle("-fx-font: 25 Helvetica");

		myClearButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myTextArea.clear();
			}
		});

		myClearButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCodeCombination hotkeyClear = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
				if (hotkeyClear.match(event)) {
					myTextArea.clear();
				}

			}
		});
		return myClearButton;

	}

	/**
	 * @return formatted TurtleBackground which initializes myTurtleGraphics
	 */
	public PanelElement createTurtleBackground() {
		myTurtleWrapper = new StackPane();
		myTurtleWrapper.setPrefHeight(Constants.PLAYGROUND_HEIGHT);
		myTurtleWrapper.setPrefWidth(Constants.PLAYGROUND_WIDTH);
		myTurtleWrapper.setBackground(
				new Background(new BackgroundFill(Constants.DEFAULT_BACKGROUND_COLOR, Constants.CORNER_RADIUS, null)));
		myTurtlePlayground = new Canvas(Constants.PLAYGROUND_WIDTH, Constants.PLAYGROUND_HEIGHT);

		myTurtleElement = createTurtleElement();
		Button infoButton = createInfoButton();
		myTurtleWrapper.getChildren().addAll(infoButton, myTurtlePlayground, myTurtleElement.getNode());
		StackPane.setAlignment(infoButton, Pos.TOP_LEFT);
		infoButton.toFront();
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

		// turtleElement.getNode().getStyleClass().add("turtle-element");
		return turtleElement;

	}

	/**
	 * Creates button that shows turtle information on click
	 * 
	 * @return
	 */
	private Button createInfoButton() {
		Button infoButton = new Button();
		
		ImageView infoImage = new ImageView(new Image((getClass().getClassLoader().getResourceAsStream("icon.png"))));
		infoImage.setFitHeight(Constants.TURTLE_INFO_BUTTON_SIZE);
		infoImage.setFitWidth(Constants.TURTLE_INFO_BUTTON_SIZE);

		infoButton.setGraphic(infoImage);
		ContextMenu cm = new ContextMenu();
		TextArea bigText = new TextArea();
		bigText.setPrefWidth(Constants.PLAYGROUND_WIDTH/3);
		
		CustomMenuItem cmi = new CustomMenuItem(bigText);
		cmi.setOnAction(e -> myController.displayTurtleInfo(bigText));
		cm.getItems().add(cmi);
		infoButton.setContextMenu(cm);
		
		return infoButton;
		
	}

	/**
	 * @return formatted VariablesElement
	 */
	public PanelElement createVariablesElement() {
		VBox variablesWrapper = new VBox();
		Label variablesLabel = new Label(Constants.getSpecification("VariablesLabel"));
		variablesLabel.getStyleClass().add("element-label");
		HBox variablesListViews = new HBox();
		ListView<String> variablesNamesListView = createVariablesNamesListView();
		ListView<String> variablesValuesListView = createVariablesValuesListView(variablesNamesListView);

		variablesListViews.getChildren().addAll(variablesNamesListView, variablesValuesListView);

		variablesWrapper.getChildren().addAll(variablesLabel, variablesListViews);

		myVariablesElement = new VariablesElement(variablesWrapper, Constants.getSpecification("VariablesElementName"));
		myVariablesElement.setNamesListView(variablesNamesListView);
		myVariablesElement.setValuesListView(variablesValuesListView);
		return myVariablesElement;

	}

	/**
	 * Helper method to create an editable listview that links to variable keys
	 * 
	 * @param names
	 * @return
	 */
	private ListView<String> createVariablesNamesListView() {
		ListView<String> namesListView = new ListView<String>();
		namesListView.setPrefSize(Constants.VARIABLES_WIDTH / 2.0, Constants.VARIABLES_HEIGHT);

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
		valuesListView.setPrefSize(Constants.VARIABLES_WIDTH / 2.0, Constants.VARIABLES_HEIGHT);

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

	/**
	 * @return formatted ColorsElement
	 */
	private PanelElement createColorsElement() {
		VBox colorsWrapper = new VBox();
		Label colorsLabel = new Label(Constants.getSpecification("ColorsLabel"));
		colorsLabel.getStyleClass().add("element-label");
		ListView<String> colorsListView = new ListView<String>();
		colorsListView.setPrefSize(Constants.COLORS_WIDTH, Constants.COLORS_HEIGHT);
		colorsListView.setCellFactory(TextFieldListCell.forListView());

		colorsWrapper.getChildren().addAll(colorsLabel, colorsListView);

		myColorsElement = new ColorsElement(colorsWrapper, Constants.getSpecification("ColorsElementName"));
		myColorsElement.setListView(colorsListView);

		return myColorsElement;
	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createCommandsElement() {
		VBox commandsWrapper = new VBox();
		Label commandsLabel = new Label(Constants.getSpecification("CommandsLabel"));
		commandsLabel.getStyleClass().add("element-label");
		ListView<String> commandsListView = new ListView<String>();
		commandsListView.setPrefSize(Constants.COMMANDS_WIDTH, Constants.COMMANDS_HEIGHT);
		commandsListView.setCellFactory(TextFieldListCell.forListView());

		commandsWrapper.getChildren().addAll(commandsLabel, commandsListView);

		myCommandsElement = new CommandsElement(commandsWrapper, Constants.getSpecification("CommandsElementName"));
		myCommandsElement.setListView(commandsListView);

		commandsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				myTextArea.setText(commandsListView.getSelectionModel().getSelectedItem());
				// myController.executeCommand(myTextArea.getText());
			}
		});

		return myCommandsElement;

	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createHistoryElement() {
		VBox historyWrapper = new VBox();
		Label historyLabel = new Label(Constants.getSpecification("HistoryLabel"));
		historyLabel.getStyleClass().add("element-label");
		ListView<String> historyListView = new ListView<String>();
		historyListView.setPrefSize(Constants.HISTORY_WIDTH, Constants.HISTORY_HEIGHT);
		historyListView.setCellFactory(TextFieldListCell.forListView());

		historyListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (!historyListView.getItems().isEmpty()) {
					String text = myTextArea.getText();
					if (text.length() > 1) {
						text = text + "\n" + historyListView.getSelectionModel().getSelectedItem();
					} else {
						text = text + historyListView.getSelectionModel().getSelectedItem();
					}

					myTextArea.setText(text);

				}
			}
		});
		historyWrapper.getChildren().addAll(historyLabel, historyListView);

		myHistoryElement = new HistoryElement(historyWrapper, Constants.getSpecification("HistoryElementName"));
		myHistoryElement.setListView(historyListView);
		return myHistoryElement;

	}

	/**
	 * @return formatted OutputElement which has a set TextArea
	 */
	public PanelElement createOutputElement() {
		VBox outputWrapper = new VBox();
		Label outputLabel = new Label(Constants.getSpecification("OutputLabel"));
		outputLabel.getStyleClass().add("element-label");
		//outputLabel.setTextAlignment(TextAlignment.CENTER);
		//outputWrapper.setPrefSize(Constants.OUTPUT_WIDTH, Constants.OUTPUT_HEIGHT);
		//outputWrapper.setBackground(new Background(new BackgroundFill(Color.WHITE, Constants.CORNER_RADIUS, null)));

		TextArea outputArea = new TextArea();
		outputArea.setPrefSize(Constants.OUTPUT_WIDTH, Constants.OUTPUT_HEIGHT);
		outputArea.setEditable(false);
		outputWrapper.getChildren().addAll(outputLabel, outputArea);
		StackPane.setAlignment(outputArea, Pos.BOTTOM_LEFT);

		myOutputElement = new OutputElement(outputWrapper, Constants.getSpecification("OutputElementName"));
		myOutputElement.setTextArea(outputArea);

		return myOutputElement;

	}
	
	private PanelElement createTurtleInfoElement(){
		VBox turtleInfoWrapper = new VBox();
		Label turtleInfoLabel = new Label(Constants.getSpecification("TurtleInfoLabel"));
		turtleInfoLabel.getStyleClass().add("element-label");
		
		ListView<String> turtleInfoListView = new ListView<String>();
		turtleInfoListView.setPrefSize(Constants.TURTLE_INFO_WIDTH, Constants.TURTLE_INFO_HEIGHT);
		turtleInfoListView.setCellFactory(TextFieldListCell.forListView());
		turtleInfoWrapper.getChildren().addAll(turtleInfoLabel, turtleInfoListView);

		myTurtleInfoElement = new TurtleInfoElement(turtleInfoWrapper, Constants.getSpecification("TurtleInfoElementName"));
		myTurtleInfoElement.setListView(turtleInfoListView);

		return myTurtleInfoElement;
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
		return myVariablesElement;
	}

	public CommandsElement getCommandsElement() {
		return myCommandsElement;
	}

	public HistoryElement getHistoryElement() {
		return myHistoryElement;
	}

	public OutputElement getOutputElement() {
		return myOutputElement;
	}

	public ColorsElement getColorsElement() {
		return myColorsElement;
	}

	public TurtleInfoElement getTurtleInfoElement(){
		return myTurtleInfoElement;
	}
	
	public TextArea getTextArea() {
		return myTextArea;
	}

}
