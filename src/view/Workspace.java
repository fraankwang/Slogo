package view;

import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.factories.HelpPageFactory;
import view.factories.MenuBarFactory;
import view.factories.PanelElementFactory;
import view.panelelements.PanelElement;

public class Workspace {

	private AnchorPane myWorkspaceRoot;
	private BorderPane myPrimaryPane;
	private TabPane myTabPane;

	private int myTabIndex;
	
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

	
	public Workspace(int tabIndex, MainController controller, Stage stage) {
		myTabIndex = tabIndex;
		myPanelElementFactory = new PanelElementFactory(controller);
		myMenuBarFactory = new MenuBarFactory(controller, stage);
		
		initializePrimaryPane();
	}
	
	private void initializePrimaryPane() {
		VBox leftColumn = myPanelElementFactory.createLeftColumn();
		VBox rightColumn = myPanelElementFactory.createRightColumn();
		MenuBar menuBar = myMenuBarFactory.createMenuBar();
		myPrimaryPane.setTop(menuBar);
		myPrimaryPane.setLeft(leftColumn);
		myPrimaryPane.setRight(rightColumn);
		initializePanelElements();
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
	
	public AnchorPane getWorkspaceRoot() {
		return myWorkspaceRoot;
	}
	
	public BorderPane getPrimaryPane() {
		return myPrimaryPane;
	}

	public AnchorPane getMyWorkspaceRoot() {
		return myWorkspaceRoot;
	}

	public BorderPane getMyPrimaryPane() {
		return myPrimaryPane;
	}

	public TabPane getMyTabPane() {
		return myTabPane;
	}

	public int getMyTabIndex() {
		return myTabIndex;
	}

	public PanelElementFactory getMyPanelElementFactory() {
		return myPanelElementFactory;
	}

	public MenuBarFactory getMyMenuBarFactory() {
		return myMenuBarFactory;
	}

	public HelpPageFactory getMyHelpPageFactory() {
		return myHelpPageFactory;
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

	public PanelElement getMyTurtleBackground() {
		return myTurtleBackground;
	}

	public PanelElement getMyTurtleElement() {
		return myTurtleElement;
	}

	public PanelElement getMyColorsElement() {
		return myColorsElement;
	}

	public PanelElement getMyTurtleInfoElement() {
		return myTurtleInfoElement;
	}

	public StackPane getMyTurtleWrapper() {
		return myTurtleWrapper;
	}

	public Canvas getMyTurtlePlayground() {
		return myTurtlePlayground;
	}

	public GraphicsContext getMyTurtleGraphics() {
		return myTurtleGraphics;
	}

}
