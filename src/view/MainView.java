/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
	private AnchorPane myPrimaryRoot;
	private Group myBasicHelpRoot;
	private Group myAdvancedHelpRoot;
	
	private Workspace myActiveWorkspace;
	private Map<Integer, Workspace> myWorkspaces;
	
	private BorderPane myPrimaryPane;
	private TabPane myTabPane;

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
	private PanelElement myTurtleInfoElement;
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
		initializeHelpRoots();

		myController.setTurtleElement(getMyTurtleElement());
//		myActiveWorkspace.setTurtleElement()
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

		showPrimaryScene();

	}

	/**
	 * Shows Help Scene
	 */
	public void showHelpScene(boolean basic) {
		if (basic) {
			myHelpScene.setRoot(myBasicHelpRoot);
		} else {
			myHelpScene.setRoot(myAdvancedHelpRoot);
		}
		
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
		AnchorPane root = new AnchorPane();
		root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		myTabPane = new TabPane();
		Tab initialTab = new Tab();
		initialTab.setText("1");
		myTabPane.getTabs().add(initialTab);
		int tabIndex = myTabPane.getTabs().size();

		//
		myWorkspaces = new HashMap<Integer, Workspace>();
		myActiveWorkspace = new Workspace(tabIndex, myController, myPrimaryStage);

		initialTab.setContent(myActiveWorkspace.getPrimaryPane());
//		tab.setContent(myPrimaryPane);

//		myPanelElementFactory = new PanelElementFactory(myController);
//		myMenuBarFactory = new MenuBarFactory(myController, myPrimaryStage);

//		VBox leftColumn = myPanelElementFactory.createLeftColumn();
//		VBox rightColumn = myPanelElementFactory.createRightColumn();
//
//		initializePanelElements();
		

//		MenuBar menuBar = myMenuBarFactory.createMenuBar();
//		myPrimaryPane.setTop(menuBar);
//		myPrimaryPane.setLeft(leftColumn);
//		myPrimaryPane.setRight(rightColumn);
		
		Button newTabButton = createNewTabButton();
		AnchorPane.setRightAnchor(newTabButton, 5.0);
		
		root.getChildren().addAll(myTabPane, newTabButton);
		myPrimaryRoot = root;

	}

	private Button createNewTabButton() {
		Button newTabButton = new Button("+");
		newTabButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				int newTabIndex = myTabPane.getTabs().size() + 1;
				myController.makeNewWorkspace(newTabIndex);
			}
		});
		return newTabButton;
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
		myTurtleInfoElement = myPanelElementFactory.getTurtleInfoElement();
	}

	/**
	 * Initializes basic and advanced help roots
	 */
	private void initializeHelpRoots() {
		myHelpPageFactory = new HelpPageFactory(myController);
		Button backButton = myHelpPageFactory.createBackButton();
		Button backButton2 = myHelpPageFactory.createBackButton();
		
		Group basicHelpRoot = new Group();
		VBox basicWrapper = new VBox();
		WebView basicHelpPage = myHelpPageFactory.createBasicHelpPage();
		basicWrapper.getChildren().addAll(backButton, basicHelpPage);
		basicHelpRoot.getChildren().add(basicWrapper);
		
		Group advancedHelpRoot = new Group();
		VBox advancedWrapper = new VBox();
		WebView advancedHelpPage = myHelpPageFactory.createAdvancedHelpPage();
		advancedWrapper.getChildren().addAll(backButton2, advancedHelpPage);
		advancedHelpRoot.getChildren().add(advancedWrapper);

		myBasicHelpRoot = basicHelpRoot;
		myAdvancedHelpRoot = advancedHelpRoot;

		myHelpScene = new Scene(advancedHelpRoot, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
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
	
	public PanelElement getMyTurtleInfoElement(){
		return myTurtleInfoElement;
	}

	public PanelElement getTurtleBackground() {
		return myTurtleBackground;
	}
	
	public PanelElement getColorsElement() {
		return myColorsElement;
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
		viewableElements.add(myTurtleInfoElement);
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

	public AnchorPane getMyPrimaryRoot() {
		return myPrimaryRoot;
	}

	public Group getMyBasicHelpRoot() {
		return myBasicHelpRoot;
	}

	public Group getMyAdvancedHelpRoot() {
		return myAdvancedHelpRoot;
	}

	public TabPane getMyTabPane() {
		return myTabPane;
	}

}
