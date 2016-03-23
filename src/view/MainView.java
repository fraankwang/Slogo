/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import view.factories.HelpPageFactory;
import view.panelelements.PanelElement;

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
	
	private TabPane myTabPane;

	private HelpPageFactory myHelpPageFactory;
	
	public MainView(Stage stage) {
		myPrimaryStage = stage;

	}

	/**
	 * Displays primaryRoot on the primaryStage
	 */
	public void init() {
		initializePrimaryRoot();
		initializeHelpRoots();

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
	 * Primary init method which pieces together all the elements to be
	 * displayed. PanelElements are created once using the factory
	 */
	private void initializePrimaryRoot() {
		AnchorPane root = new AnchorPane();
		root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		myWorkspaces = new HashMap<Integer, Workspace>();
		myTabPane = new TabPane();
		
		int initialTabIndex = Constants.INITIAL_TAB_INDEX;
		Workspace initialWorkspace = myController.makeNewWorkspace(initialTabIndex, myPrimaryStage); 		
		Tab initialTab = myController.makeNewTab(initialWorkspace, initialTabIndex);
		
		myTabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
			int currentTabIndex = myTabPane.getSelectionModel().getSelectedIndex();
			myActiveWorkspace = myWorkspaces.get(currentTabIndex);
			myController.setTurtleElement(myActiveWorkspace.getMyTurtleElement());
	        myController.setMyActiveModel(currentTabIndex);
	    });
		
		myTabPane.getTabs().add(initialTab);
		
		Button newTabButton = createNewTabButton();
		AnchorPane.setRightAnchor(newTabButton, 5.0);
		
		root.getChildren().addAll(myTabPane, newTabButton);
		myPrimaryRoot = root;

	}

	/**
	 * Creates Add Tab button
	 * @return
	 */
	private Button createNewTabButton() {
		Button newTabButton = new Button(Constants.getSpecification("NewTabButton"));
		newTabButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				int newTabIndex = myTabPane.getTabs().size();
				Workspace workspace = myController.makeNewWorkspace(newTabIndex, myPrimaryStage);
				Tab newTab = myController.makeNewTab(workspace, newTabIndex);
				myController.makeNewModel(newTabIndex);
				myTabPane.getTabs().add(newTab);
			}
		});
		return newTabButton;
		
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

	/**
	 * Links controller with MainView
	 * @param myController
	 */
	public void linkController(MainController myController) {
		this.myController = myController;

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

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public List<PanelElement> getViewableElements() {
		return myActiveWorkspace.getViewableElements();
	}

	public void setMyPrimaryStage(Stage myPrimaryStage) {
		this.myPrimaryStage = myPrimaryStage;
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

	public Map<Integer, Workspace> getMyWorkspaces() {
		return myWorkspaces;
	}
	
	public Workspace getMyActiveWorkspace() {
		return myActiveWorkspace;
	}

	public void setMyActiveWorkspace(Workspace newWorkspace) {
		myActiveWorkspace = newWorkspace;
		
	}

}
