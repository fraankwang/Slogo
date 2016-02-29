//package view;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import constants.Constants;
//import controller.MainController;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Node;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.control.Button;
//import javafx.scene.control.Control;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.TextFieldListCell;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Text;
//
//public class Panel {
//
//	private MainController myController;
//
//	private static final int LEFT_COLUMN_WIDTH = Constants.LEFT_COLUMN_WIDTH;
//	private static final int PLAYGROUND_HEIGHT = Constants.PLAYGROUND_HEIGHT;
//	private static final int RIGHT_COLUMN_WIDTH = Constants.RIGHT_COLUMN_WIDTH;
//	private static final int RIGHT_COLUMN_ELEMENT_HEIGHT = Constants.RIGHT_COLUMN_ELEMENT_HEIGHT;
//
//	private static final double ELEMENT_INSET_HORIZONTAL = Constants.ELEMENT_INSET_HORIZONTAL;
//	private static final double ELEMENT_INSET_VERTICAL = Constants.ELEMENT_INSET_VERTICAL;
//
//	private StackPane myTurtleBackground;
//	private GraphicsContext turtleBox;
//
//	public Panel(MainController controller) {
//		myController = controller;
//
//	}
//
//	/**
//	 * Helper UI-creating function that adds tool bar elements and links them to
//	 * myController
//	 * 
//	 * @return formatted VBox
//	 */
//	public VBox createRightColumn() {
//
//		/**
//		 * NOTES ON THIS: 1. Outer most wrapper is a VBox with 4 elements:
//		 * Variables, Commands, History, Output Console 2. Each of the first 3
//		 * elements should be scrollpanes, last element can just be a Text 2a.
//		 * Scrollpanes have a setContent(Node) method, so you have to have
//		 * another wrapper inside each scrollpane NOTE: I think a GridPane would
//		 * be good, and you can do some CSS formatting to make it neat. It would
//		 * work well with the layout for picking variables/commands 2b. For
//		 * formatting purposes, you might want to make OutputConsole a stackpane
//		 * with a text as a child 3. Make sure to use VBox.setMargins (look at
//		 * Toolbar.java lines 106-112 for reference) for the 4 elements to make
//		 * them look neat 4. Whatever your element is that you're actually
//		 * modifying (i.e. Map or ArrayList), make it a global variable and add
//		 * getters/setters, then link them to myController, so ModelTransformer
//		 * has access to them 4a. If that doesn't make sense, look at
//		 * myTurtleBox (GraphicsContext) and how it's traced
//		 */
//
//		Text variablesLabel = new Text(Constants.getSpecification("Variables"));
//		ListView<String> variablesListView = createVariablesListView();
//		VBox variablesWrapper = new VBox();
//		variablesWrapper.getChildren().addAll(variablesLabel, variablesListView);
//
//		Text commandsLabel = new Text(Constants.getSpecification("Commands"));
//		ListView<String> commandsListView = createCommandsListView();
//		VBox commandsWrapper = new VBox();
//		commandsWrapper.getChildren().addAll(commandsLabel, commandsListView);
//
////		Text historyLabel = new Text(Constants.getSpecification("History"));
//		ListView<String> historyListView = createHistoryListView();
//		VBox historyWrapper = new VBox();
//		historyWrapper.getChildren().addAll(historyLabel, historyListView);
//
//		Text outputLabel = new Text(Constants.getSpecification("Output"));
//		StackPane outputPane = createOutputPane();
//		VBox outputWrapper = new VBox();
//		outputWrapper.getChildren().addAll(outputLabel, outputPane);
//
//		List<Node> allWrappers = Arrays.asList(variablesWrapper, commandsWrapper, historyWrapper, outputWrapper);
//		setMargins(allWrappers);
//
//		// TODO: change all print statements to show in output box instead of
//		// Eclipse console
//		VBox vb = new VBox();
//		vb.getChildren().addAll(allWrappers);
//		return vb;
//	}
//
//	/**
//	 * @return formatted ScrollPane wrapper that contains StackPane with
//	 *         Variables elements
//	 */
//	private ListView<String> createVariablesListView() {
//		ObservableList<String> test = FXCollections.observableArrayList("variable0", "variable1");
//		ListView<String> listView = new ListView<String>(test);
//		listView.setPrefSize(RIGHT_COLUMN_WIDTH, RIGHT_COLUMN_ELEMENT_HEIGHT);
//
//		listView.setEditable(true);
//		listView.setCellFactory(TextFieldListCell.forListView());
//		//
//		// listView.setOnEditCommit(new
//		// EventHandler<ListView.EditEvent<String>>() {
//		// @Override
//		// public void handle(ListView.EditEvent<String> t) {
//		// listView.getItems().set(t.getIndex(), t.getNewValue());
//		// System.out.println("setOnEditCommit");
//		// }
//		// });
//		//
//		// listView.setOnEditCancel(new
//		// EventHandler<ListView.EditEvent<String>>() {
//		// @Override
//		// public void handle(ListView.EditEvent<String> t) {
//		// System.out.println("setOnEditCancel");
//		// }
//		// });
//		return listView;
//	}
//
//	/**
//	 * @return formatted ScrollPane wrapper that contains StackPane with
//	 *         commands elements
//	 */
//	private ListView<String> createCommandsListView() {
//		ObservableList<String> test = FXCollections.observableArrayList("command0", "command1");
//		ListView<String> listView = new ListView<String>(test);
//		listView.setPrefSize(RIGHT_COLUMN_WIDTH, RIGHT_COLUMN_ELEMENT_HEIGHT);
//
//		listView.setCellFactory(TextFieldListCell.forListView());
//
//		return listView;
//	}
//
//
//
//
//
//	/**
//	 * Takes @param items, checks what type of Object they are, and applies
//	 * relevant insets
//	 */
//	private void setMargins(List<Node> items) {
//		Insets insets = new Insets(ELEMENT_INSET_HORIZONTAL, ELEMENT_INSET_VERTICAL, ELEMENT_INSET_HORIZONTAL,
//				ELEMENT_INSET_VERTICAL);
//
//		for (Node item : items) {
//			VBox.setMargin(item, insets);
//		}
//	}
//
//	
//	// =========================================================================
//	// Getters and Setters
//	// =========================================================================
//
//	private void setTurtleBox(GraphicsContext tb) {
//		turtleBox = tb;
//	}
//
//	public GraphicsContext getTurtleBox() {
//		return turtleBox;
//	}
//
//	public StackPane getTurtleBackground() {
//		return myTurtleBackground;
//	}
//}
