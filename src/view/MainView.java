/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.ArrayList;
import java.util.List;
import constants.Constants;
import controller.MainController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;

import javafx.stage.Stage;
import view.factories.HelpPageFactory;
import view.factories.MenuBarFactory;
import view.factories.PanelElementFactory;
import view.panelelements.PanelElement;
import view.panelelements.TurtleElement;

public class MainView {

	private static final double SCENE_WIDTH = Constants.SCENE_WIDTH;
	private static final double SCENE_HEIGHT = Constants.SCENE_HEIGHT;

	private Stage myPrimaryStage;
	private Scene myPrimaryScene;
	private Scene myHelpScene;
	private MainController myController;
	private Group myPrimaryRoot;
	private Group myHelpRoot;
	private BorderPane myPrimaryPane;

	private PanelElementFactory myPanelElementFactory;
	private MenuBarFactory myMenuBarFactory;
	private HelpPageFactory myHelpPageFactory;
	private PanelElement myOutputElement;
	private PanelElement myHistoryElement;
	private PanelElement myCommandsElement;
	private PanelElement myVariablesElement;
	private PanelElement myTurtleBackground;
	private PanelElement myTurtleElement;
	private PanelElement myColorsElement;
	private StackPane myTurtleWrapper;
	private Canvas myTurtlePlayground;
	private GraphicsContext myTurtleGraphics;

	public MainView(Stage stage) {
		myPrimaryStage = stage;

	}

	/**
	 * Displays primaryRoot on the primaryStage
	 */
	public void init() {
		initializePrimaryRoot();
		initializeHelpRoot();

		myController.setTurtleElement(getMyTurtleElement());

		myPrimaryScene = new Scene(myPrimaryRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		myPrimaryScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent t) {
				KeyCode key = t.getCode();
				if (key == KeyCode.ESCAPE) {
					myPrimaryStage.close();
				}
			}
		});

		myHelpScene = new Scene(myHelpRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
		showPrimaryScene();

	}

	/**
	 * Shows Help Scene
	 */
	public void showHelpScene() {
		myPrimaryStage.setScene(myHelpScene);
		myPrimaryStage.show();

	}

	/**
	 * Shows Primary Scene
	 */
	public void showPrimaryScene() {
		myPrimaryStage.setScene(myPrimaryScene);
		myPrimaryStage.show();
	}

	/**
	 * Primary init method which pieces together all the elements to be
	 * displayed. PanelElements are created once using the factory
	 */
	private void initializePrimaryRoot() {
		Group root = new Group();
		root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		myPrimaryPane = new BorderPane();
		root.getChildren().add(myPrimaryPane);

		myPanelElementFactory = new PanelElementFactory(myController);
		myMenuBarFactory = new MenuBarFactory(myController, myPrimaryStage);

		VBox leftColumn = myPanelElementFactory.createLeftColumn();
		VBox rightColumn = myPanelElementFactory.createRightColumn();

		initializePanelElements();

		MenuBar menuBar = myMenuBarFactory.createMenuBar();
		myPrimaryPane.setTop(menuBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;

	}

	/**
	 * Utilizes factory to initialize PanelElements
	 */
	private void initializePanelElements() {
		myTurtleGraphics = myPanelElementFactory.getTurtleGraphics();
		myTurtleBackground = myPanelElementFactory.getTurtleBackground();
		myTurtleElement = myPanelElementFactory.getTurtleElement();
		myTurtleWrapper = myPanelElementFactory.getTurtleWrapper();
		myTurtlePlayground = myPanelElementFactory.getTurtlePlayground();

		myVariablesElement = myPanelElementFactory.getVariablesElement();
		myCommandsElement = myPanelElementFactory.getCommandsElement();
		myHistoryElement = myPanelElementFactory.getHistoryElement();
		myOutputElement = myPanelElementFactory.getOutputElement();
		myColorsElement = myPanelElementFactory.getColorsElement();

	}

	/**
	 * 
	 */
	private void initializeHelpRoot() {
		Group helpRoot = new Group();
		VBox wrapper = new VBox();
		myHelpPageFactory = new HelpPageFactory(myController);

		Button backButton = myHelpPageFactory.createBackButton();
		WebView htmlPage = myHelpPageFactory.createHTMLPage();

		wrapper.getChildren().addAll(backButton, htmlPage);
		helpRoot.getChildren().add(wrapper);
		myHelpRoot = helpRoot;

	}

	public void linkController(MainController myController) {
		this.myController = myController;
		myController.setTurtleElement(myTurtleElement);
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public GraphicsContext getMyTurtleGraphics() {
		return myTurtleGraphics;
	}

	public PanelElement getMyTurtleElement() {
		return myTurtleElement;
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
		viewableElements.add(myTurtleElement);
		viewableElements.add(myColorsElement);
		return viewableElements;

	}

	public void setMyPrimaryStage(Stage myPrimaryStage) {
		this.myPrimaryStage = myPrimaryStage;
	}

	public void setTurtleImage(String image) {
		ImageView newImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(image + ".jpg")));
		newImage.setFitWidth(Constants.TURTLE_ELEMENT_WIDTH);
		newImage.setFitHeight(Constants.TURTLE_ELEMENT_HEIGHT);
		Double oldX = myTurtleElement.getNode().getTranslateX();
		Double oldY = myTurtleElement.getNode().getTranslateY();
		((TurtleElement) myTurtleElement).setTurtleImage(newImage);
		myTurtleWrapper.getChildren().removeAll(myTurtlePlayground, myTurtleElement.getNode());
		myTurtleWrapper.getChildren().addAll(myTurtlePlayground, myTurtleElement.getNode());
		((TurtleElement) myTurtleElement).moveTurtleImage(oldX, oldY);
	}

	public void setTurtleBackgroundColor(Color color) {
		myTurtleBackground.setBackground(new Background(new BackgroundFill(color, Constants.CORNER_RADIUS, null)));
	}

	public Group getMyPrimaryRoot() {
		return myPrimaryRoot;
	}

	public Group getMyHelpRoot() {
		return myHelpRoot;
	}

}
