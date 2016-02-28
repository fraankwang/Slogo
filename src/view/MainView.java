/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

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
	private Node myOutputBox;
	private Node myHistoryBox;
	private GraphicsContext myTurtleBox;
	private StackPane myTurtleBackground;
	private Node myCommandsBox;
	private Node myVariablesBox;

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
	 * displayed
	 */
	private void initializeRoot() {
		Group root = new Group();
		
		final Menu menu1 = new Menu("File");
		MenuItem openFile = new CheckMenuItem("Open");
		CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
		customMenuItem.setHideOnClick(false);
		
		openFile.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	((CheckMenuItem) openFile).setSelected(true);
		        System.out.println("Open File");
		    }
		});
		
		/**
		 * TODO: put everything in MenuBar, setHideOnClick, images for picking turtle, upload new images
		 * dividers etc within the menu, help button that opens HTML page(??)
		 */
		openFile.setGraphic(new ImageView(new Image("basic.jpg")));
		
		menu1.getItems().addAll(openFile, customMenuItem);
		final Menu menu2 = new Menu("Options");
		final Menu menu3 = new Menu("Help");

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menu1, menu2, menu3);
	
		
		
		myPrimaryPane = new BorderPane();
		root.getChildren().add(myPrimaryPane);

		Toolbar tb = new Toolbar(myController);
		Panel panel = new Panel(myController);
		
		HBox toolBar = tb.createToolBar();
		VBox leftColumn = panel.createLeftColumn();
		VBox rightColumn = panel.createRightColumn();

		setMyTurtleBox(panel.getTurtleBox());
		setMyTurtleBackground(panel.getTurtleBackground());
		//set other stuff too
		
		
		myPrimaryPane.setTop(menuBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);

		myPrimaryRoot = root;
	}

	// =========================================================================
	// Getters and Setters
	// =========================================================================

	public void setController(MainController controller) {
		myController = controller;
	}

	public Node getMyOutputBox() {
		return myOutputBox;
	}

	public Node getMyHistoryBox() {
		return myHistoryBox;
	}

	public void setMyTurtleBox(GraphicsContext gc) {
		myTurtleBox = gc;
	}
	
	public GraphicsContext getMyTurtleBox() {
		return myTurtleBox;
	}

	public Node getMyCommandsBox() {
		return myCommandsBox;
	}

	public Node getMyVariablesBox() {
		return myVariablesBox;
	}

	public StackPane getTurtleBackground() {
		return myTurtleBackground;
	}
	
	private void setMyTurtleBackground(StackPane turtleBackground) {
		myTurtleBackground = turtleBackground;
	}

	public void setTurtleBackgroundColor(Color color) {
		myTurtleBackground.setBackground(new Background(new BackgroundFill(color, Constants.CORNER_RADIUS, null)));
		
	}

}
