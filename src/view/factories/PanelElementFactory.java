/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.factories;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
	private GridPane myGridPane;
	private TilePane myTilePane;

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
	 * Public factory method that returns filled GridPane
	 * 
	 * @return
	 */
	public GridPane createGridPane() {
		initializeGridPane();
		fillGridPane();
		return myGridPane;
	}

	/**
	 * Initializes myGridPane with proper formatting
	 */
	private void initializeGridPane() {
		myGridPane = new GridPane();
		myGridPane.setPadding(new Insets(5, 5, 5, 5));
		myGridPane.setHgap(5.0);
		myGridPane.setVgap(5.0);
		myGridPane.setPrefSize(Constants.SCENE_WIDTH, Constants.SCENE_HEIGHT);

		ColumnConstraints column0 = new ColumnConstraints();
		column0.setPercentWidth(50);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		myGridPane.getColumnConstraints().addAll(column0, column1);
	}

	/**
	 * Adds PanelElements to GridPane
	 */
	private void fillGridPane() {
		myGridPane.add(myTurtleBackground.getNode(), 0, 0);
		myGridPane.add(myTurtleElement.getNode(), 0, 0);
		GridPane.setHalignment(myTurtleElement.getNode(), HPos.CENTER);
		myGridPane.add(makeInputWrapper(), 0, 1);
		initializeElementsTilePane();
		myGridPane.add(myTilePane, 1, 0, 1, 2);
	}

	/**
	 * Adds PanelElements to TilePane
	 */
	private void initializeElementsTilePane() {
		myTilePane = new TilePane();
		myTilePane.setPrefColumns(2);
		myTilePane.setPrefRows(3);
		myTilePane.setPrefTileWidth(Constants.PANEL_ELEMENT_WIDTH);
		myTilePane.setPrefTileHeight(Constants.PANEL_ELEMENT_HEIGHT);
		myTilePane.setHgap(5.0);
		myTilePane.setVgap(5.0);
		myTilePane.getChildren().addAll(myVariablesElement.getNode(), myColorsElement.getNode(),
				myCommandsElement.getNode(), myHistoryElement.getNode(), myTurtleInfoElement.getNode(),
				myOutputElement.getNode());
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
		TextArea bigText = new TextArea(Constants.getSpecification("InfoButtonDefaultMessage"));
		bigText.setPrefWidth(Constants.INFO_MENU_HEIGHT);
		bigText.setPrefHeight(Constants.INFO_MENU_WIDTH);

		CustomMenuItem cmi = new CustomMenuItem(bigText);
		cmi.setHideOnClick(false);
		cm.getItems().add(cmi);
		cm.setOnShown(e -> myController.displayTurtleInfo(bigText));
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
		namesListView.setPrefSize(Constants.PANEL_ELEMENT_WIDTH / 2.0, Constants.PANEL_ELEMENT_HEIGHT);

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
		valuesListView.setPrefSize(Constants.PANEL_ELEMENT_WIDTH / 2.0, Constants.PANEL_ELEMENT_HEIGHT);

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

		HBox colorsListViews = new HBox();
		ListView<String> colorIntegersListView = createColorIntegersListView();
		ListView<String> colorValuesListView = createColorValuesListView(colorIntegersListView);

		colorsListViews.getChildren().addAll(colorIntegersListView, colorValuesListView);
		colorsWrapper.getChildren().addAll(colorsLabel, colorsListViews);

		ListView<String> colorsListView = new ListView<String>();
		// colorsListView.setPrefSize(Constants.COLORS_WIDTH,
		// Constants.COLORS_HEIGHT);
		colorsListView.setCellFactory(TextFieldListCell.forListView());

		myColorsElement = new ColorsElement(colorsWrapper, Constants.getSpecification("ColorsElementName"));
		myColorsElement.setIntegersListView(colorIntegersListView);
		myColorsElement.setValuesListView(colorValuesListView);

		return myColorsElement;
	}

	/**
	 * Helper method to create an editable listview that links to variable keys
	 * 
	 * @param names
	 * @return
	 */
	private ListView<String> createColorIntegersListView() {
		ListView<String> colorIntegersListView = new ListView<String>();
		colorIntegersListView.setPrefSize(Constants.PANEL_ELEMENT_WIDTH / 2.0, Constants.PANEL_ELEMENT_HEIGHT);
		return colorIntegersListView;
	}

	/**
	 * Helper method to create an editable listview that links to variable
	 * values
	 * 
	 * @param names
	 * @return
	 */
	private ListView<String> createColorValuesListView(ListView<String> names) {
		ListView<String> colorValuesListView = new ListView<String>();
		colorValuesListView.setPrefSize(Constants.PANEL_ELEMENT_WIDTH / 2.0, Constants.PANEL_ELEMENT_HEIGHT);
		return colorValuesListView;
	}

	/**
	 * @return formatted HistoryElement
	 */
	public PanelElement createCommandsElement() {
		VBox commandsWrapper = new VBox();
		Label commandsLabel = new Label(Constants.getSpecification("CommandsLabel"));
		commandsLabel.getStyleClass().add("element-label");
		ListView<String> commandsListView = new ListView<String>();
		// commandsListView.setPrefSize(Constants.COMMANDS_WIDTH,
		// Constants.COMMANDS_HEIGHT);
		commandsListView.setCellFactory(TextFieldListCell.forListView());

		commandsWrapper.getChildren().addAll(commandsLabel, commandsListView);

		myCommandsElement = new CommandsElement(commandsWrapper, Constants.getSpecification("CommandsElementName"));
		myCommandsElement.setListView(commandsListView);

		commandsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (commandsListView.getSelectionModel().getSelectedItem().length() != 0) {
					myTextArea.setText(commandsListView.getSelectionModel().getSelectedItem());
					// myController.executeCommand(myTextArea.getText());
				}
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
		// historyListView.setPrefSize(Constants.HISTORY_WIDTH,
		// Constants.HISTORY_HEIGHT);
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

		TextArea outputArea = new TextArea();
		// outputArea.setPrefSize(Constants.OUTPUT_WIDTH,
		// Constants.OUTPUT_HEIGHT);
		outputArea.setEditable(false);
		outputWrapper.getChildren().addAll(outputLabel, outputArea);
		StackPane.setAlignment(outputArea, Pos.BOTTOM_LEFT);

		myOutputElement = new OutputElement(outputWrapper, Constants.getSpecification("OutputElementName"));
		myOutputElement.setTextArea(outputArea);

		return myOutputElement;

	}

	/**
	 * @return formatted TurtleInfoElement
	 */
	private PanelElement createTurtleInfoElement() {
		VBox turtleInfoWrapper = new VBox();
		Label turtleInfoLabel = new Label(Constants.getSpecification("TurtleInfoLabel"));
		turtleInfoLabel.getStyleClass().add("element-label");

		ListView<String> turtleInfoListView = new ListView<String>();
		// turtleInfoListView.setPrefSize(Constants.TURTLE_INFO_WIDTH,
		// Constants.TURTLE_INFO_HEIGHT);
		turtleInfoListView.setCellFactory(TextFieldListCell.forListView());
		turtleInfoWrapper.getChildren().addAll(turtleInfoLabel, turtleInfoListView);

		myTurtleInfoElement = new TurtleInfoElement(turtleInfoWrapper,
				Constants.getSpecification("TurtleInfoElementName"));
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

	public TurtleInfoElement getTurtleInfoElement() {
		return myTurtleInfoElement;
	}

	public TextArea getTextArea() {
		return myTextArea;
	}

}
