/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.util.ArrayList;
import java.util.List;
import controller.MainController;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.factories.HelpPageFactory;
import view.factories.MenuBarFactory;
import view.factories.PanelElementFactory;
import view.panelelements.PanelElement;

public class Workspace {

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
		myPrimaryPane = new BorderPane();
	}

	/**
	 * public initialize method which must be called after the constructor
	 * initializes the factories
	 */
	public void initialize() {
		initializePrimaryPane();
	}

	/**
	 * private init method which must fills the primaryPane
	 */
	private void initializePrimaryPane() {
		GridPane myGridPane = myPanelElementFactory.createGridPane();
		initializePanelElements();

		MenuBar menuBar = myMenuBarFactory.createMenuBar();

		myPrimaryPane.setTop(menuBar);
		myPrimaryPane.setCenter(myGridPane);
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

	// =========================================================================
	// Getters and Setters
	// =========================================================================

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

	public BorderPane getPrimaryPane() {
		return myPrimaryPane;
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
