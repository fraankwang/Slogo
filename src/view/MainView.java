/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MainView {

	private static final int SCENE_WIDTH = 1000;
	private static final int SCENE_HEIGHT = 1000;

	private Stage myPrimaryStage;
	private MainController myController;
	private Group myPrimaryRoot;
	private BorderPane myPrimaryPane;
	
	private PanelElementFactory myPanelElementFactory;
	private MenuBarFactory myMenuBarFactory;
	private PanelElement myOutputElement;
	private PanelElement myHistoryElement;
	private PanelElement myCommandsElement;
	private PanelElement myVariablesElement;
	private PanelElement myTurtleBackground;
	private GraphicsContext myTurtleGraphics;
	
	public MainView(Stage stage) {
		myPrimaryStage = stage;

	}

	/**
	 * Displays primaryRoot on the primaryStage
	 */
	public void display() {
		initializeRoot();
		Scene scene = new Scene(myPrimaryRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		myPrimaryStage.setScene(scene);
		myPrimaryStage.show();

	}

	/**
	 * Primary init method which pieces together all the elements to be
	 * displayed. PanelElements are created once using the factory
	 */
	private void initializeRoot() {
		Group root = new Group();
		myPrimaryPane = new BorderPane();
		root.getChildren().add(myPrimaryPane);

		myPanelElementFactory = new PanelElementFactory(myController);
		myMenuBarFactory = new MenuBarFactory(myController);
		
		VBox leftColumn = myPanelElementFactory.createLeftColumn();
		VBox rightColumn = myPanelElementFactory.createRightColumn();
		
		setMyTurtleGraphics(myPanelElementFactory.getTurtleGraphics());
		setMyTurtleBackground(myPanelElementFactory.getTurtleBackground());
		setMyVariablesElement(myPanelElementFactory.getVariablesElement());	
		setMyCommandsElement(myPanelElementFactory.getCommandsElement());
		setMyHistoryElement(myPanelElementFactory.getHistoryElement());
		setMyOutputElement(myPanelElementFactory.getOutputElement());
//		MenuBar menuBar = myMenuBarFactory.createMenuBar();
		
//		myPrimaryPane.setTop(menuBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;
	}


	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public GraphicsContext getMyTurtleGraphics() {
		return myTurtleGraphics;
	}

	public PanelElement getMyOutputElement() {
		return myOutputElement;
	}

	public PanelElement getMyHistoryElement() {
		return myHistoryElement;
	}

	public PanelElement getMyCommandsElement() {
		return myCommandsElement;
	}

	public PanelElement getMyVariablesElement() {
		return myVariablesElement;
	}

	public PanelElement getTurtleBackground() {
		return myTurtleBackground;
	}
	
	public List<PanelElement> getViewableElements() {
		List<PanelElement> viewableElements = new ArrayList<PanelElement>();
		viewableElements.add(myVariablesElement);
		viewableElements.add(myCommandsElement);
		viewableElements.add(myHistoryElement);
		viewableElements.add(myOutputElement);
		viewableElements.add(myTurtleBackground);
		return viewableElements;
		
	}
	
	public void setController(MainController controller) {
		myController = controller;
	}

	public void setMyPrimaryStage(Stage myPrimaryStage) {
		this.myPrimaryStage = myPrimaryStage;
	}

	public void setMyController(MainController myController) {
		this.myController = myController;
	}

	public void setMyPrimaryRoot(Group myPrimaryRoot) {
		this.myPrimaryRoot = myPrimaryRoot;
	}

	public void setMyPrimaryPane(BorderPane myPrimaryPane) {
		this.myPrimaryPane = myPrimaryPane;
	}

	public void setMyPanelElementFactory(PanelElementFactory myPanelElementFactory) {
		this.myPanelElementFactory = myPanelElementFactory;
	}
	
	public void setMyTurtleGraphics(GraphicsContext gc) {
		myTurtleGraphics = gc;
	}
	
	private void setMyTurtleBackground(PanelElement turtleBackground) {
		myTurtleBackground = turtleBackground;
	}

	public void setTurtleBackgroundColor(Color color) {
		myTurtleBackground.setBackground(new Background(new BackgroundFill(color, Constants.CORNER_RADIUS, null)));
	}
	
	public void setMyOutputElement(PanelElement outputElement) {
		this.myOutputElement = outputElement;
	}

	public void setMyHistoryElement(PanelElement historyElement) {
		this.myHistoryElement = historyElement;
	}

	public void setMyCommandsElement(PanelElement commandsElement) {
		this.myCommandsElement = commandsElement;
	}

	public void setMyVariablesElement(PanelElement variablesElement) {
		this.myVariablesElement = variablesElement;
	}

}
