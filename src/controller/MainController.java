package controller;

import java.util.*;
import configuration.ConfigurationInfo;
import constants.Constants;
import controller.ModelTransformer;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MainModel;
import view.MainView;
import view.Workspace;
import view.panelelements.PanelElement;
import view.panelelements.TurtleElement;

public class MainController {

	private MainView myView;
	private MainModel myActiveModel;
	private Map<Integer, MainModel> myModels;
	private ModelTransformer myTransformer;
	private Timeline myTimeline;

	public MainController(MainView view) {
		myView = view;
		myTransformer = new ModelTransformer(this);
		myModels = new HashMap<Integer, MainModel>();
		int currentModelIndex = Constants.INITIAL_TAB_INDEX;
		MainModel initialModel = new MainModel(myTransformer.getLanguage());
		myModels.put(currentModelIndex, initialModel);
		myActiveModel = initialModel;
	}

	/**
	 * Executes a command whenever user enters text into the input area. Step 1
	 * sends information to be parsed and updated in the model Step 2 gets the
	 * most recent model information and translates it back for the view
	 * 
	 * @param input
	 *            the command(s) the user types
	 */
	public void executeCommand(String input) {
		readCommand(input);
		refreshDisplay();
	}

	/**
	 * Part 1 of executeCommand. Calls corresponding method in myModel, which
	 * deals with parsing and implementing it.
	 * 
	 * @param input
	 */
	private void readCommand(String input) {
		myActiveModel.readCommand(input);
	}

	/**
	 * Part 2 of executeCommand. Refreshes every UI elements in the view
	 */
	public void refreshDisplay() {
		myTransformer.transformOutputElement((Queue<String>) myActiveModel.getMyOutputs());
		myTransformer.transformHistoryElement((Queue<String>) myActiveModel.getMyHistory());
		myTransformer.transformTurtleGraphics(myActiveModel.getMyPlayground());
		myTransformer.transformVariablesElement(myActiveModel.getMyVariables());
		myTransformer.transformCommandsElement(myActiveModel.getMyUserCommands());
		myTransformer.transformColorsElement(myActiveModel.getPaletteMap());
		myTransformer.transformTurtleInfoElement(myActiveModel.getMyPlayground().getTurtleList());

	}

	/**
	 * Creates a new model with associated index and stores it in a Map of
	 * MainModels
	 * 
	 * @param newTabIndex
	 */
	public void makeNewModel(int newTabIndex) {
		MainModel newModel = new MainModel(myTransformer.getLanguage());
		myModels.put(newTabIndex, newModel);
	}

	/**
	 * Changes MainModel to correspond with tab change
	 * 
	 * @param newTabIndex
	 */
	public void setActiveModel(int newTabIndex) {
		myActiveModel = myModels.get(newTabIndex);
	}

	/**
	 * Grabs current turtle information and displays it in given TextArea
	 * 
	 * @param textArea
	 */
	public void displayTurtleInfo(TextArea textArea) {
		String language = "Language: " + myActiveModel.getLanguage() + "\n";
		String ID = "Turtle ID: " + Integer.toString(myActiveModel.getMyPlayground().getCurrentTurtleID()) + "\n";
		String orientation = "Orientation: " + myActiveModel.getMyPlayground().getCurrentTurtle().getOrientation() % 360
				+ "\n";

		String penUp;
		if (myActiveModel.getMyPlayground().getCurrentTurtle().getPenDown()) {
			penUp = "Pen is: down" + "\n";
		} else {
			penUp = "Pen is: up" + "\n";
		}

		String penColor = "Pen color: " + myActiveModel.getMyPlayground().getCurrentPenColor() + "\n";

		Double xCoord = myView.getMyActiveWorkspace().getMyTurtleElement().getNode().getTranslateX();
		Double yCoord = myView.getMyActiveWorkspace().getMyTurtleElement().getNode().getTranslateY();
		if (yCoord != 0) {
			yCoord *= -1;
		}
		String x = "X Coordinate: " + Double.toString(xCoord) + "\n";
		String y = "Y Coordinate: " + Double.toString(yCoord);
		textArea.setText(language + ID + orientation + penUp + penColor + x + y);

	}

	/**
	 * Updates all model information given packaged configuration info
	 * 
	 * @param configInfo
	 */
	public void updateConfiguration(ConfigurationInfo configInfo) {
		myActiveModel.setLanguage(configInfo.getMyLanguage());
		myTransformer.setLanguage(configInfo.getMyLanguage());
		myActiveModel.getMyPlayground().setCurrentPenColor(configInfo.getMyPenColor());
		myActiveModel.getMyPlayground().setBackgroundColor(configInfo.getMyBackgroundColor());
		myActiveModel.getMyPlayground().getCurrentTurtle().setPenWidth(configInfo.getMyPenWidth());
		myActiveModel.setMyVariables(configInfo.getMyVariables());
		myActiveModel.setMyUserCommands(configInfo.getMyCommands());
		myActiveModel.setMyPalette(configInfo.getMyPalette());

	}

	/**
	 * Gathers all necessary information to be passed to XML generator
	 * 
	 * @return
	 */
	public ConfigurationInfo gatherConfigurationInfo() {
		ConfigurationInfo configInfo = new ConfigurationInfo();
		configInfo.setMyVariables(myActiveModel.getMyVariables());
		configInfo.setMyCommands(myActiveModel.getMyUserCommands());
		configInfo.setMyLanguage(myActiveModel.getLanguage());
		configInfo.setMyBackgroundColor(myActiveModel.getMyPlayground().getMyBackgroundColor());
		configInfo.setMyPenColor(myActiveModel.getMyPlayground().getCurrentPenColor());
		configInfo.setMyPenWidth(myActiveModel.getMyPlayground().getCurrentTurtle().getPenWidth());
		configInfo.setMyPalette(myActiveModel.getPalette());
		return configInfo;

	}

	/**
	 * Deletes stored previously run commands and moves turtle position back to
	 * start
	 */
	public void resetTurtlePosition() {
		myView.getMyActiveWorkspace().getMyTurtleGraphics().clearRect(0, 0, Constants.PLAYGROUND_WIDTH,
				Constants.PLAYGROUND_HEIGHT);
		myActiveModel.getMyPlayground().getCurrentTurtle().clearTurtleCoordinates();
		myActiveModel.getMyPlayground().setTurtleHome();
		TurtleElement turtleElement = (TurtleElement) myView.getMyActiveWorkspace().getMyTurtleElement();
		turtleElement.moveTurtleImage(0.0, 0.0);
		myTransformer.transformTurtleGraphics(myActiveModel.getMyPlayground());

	}

	/**
	 * Sets workspace to active workspace, updates TurtleElement, and
	 * initializes workspace
	 * 
	 * @param newTabIndex
	 * @param primaryStage
	 * @return
	 */
	public Workspace makeNewWorkspace(int newTabIndex, Stage primaryStage) {

		Workspace newWorkspace = new Workspace(newTabIndex, this, primaryStage);
		myView.setMyActiveWorkspace(newWorkspace);
		newWorkspace.initialize();
		setTurtleElement(newWorkspace.getMyTurtleElement());
		myView.getMyWorkspaces().put(newTabIndex, newWorkspace);

		return newWorkspace;

	}

	/**
	 * Creates new tab and sets workspace content to be tab content
	 * 
	 * @param workspace
	 * @param newTabIndex
	 * @return
	 */
	public Tab makeNewTab(Workspace workspace, int newTabIndex) {
		Tab tab = new Tab();
		tab.setText(Constants.getSpecification("WorkspaceTabName") + " " + Integer.toString(newTabIndex));
		tab.setContent(workspace.getPrimaryPane());
		return tab;

	}

	// =========================================================================
	// Modifiers, Getters, and Setters
	// =========================================================================

	public MainView getMyView() {
		return myView;
	}

	public List<PanelElement> getViewableElements() {
		return myView.getViewableElements();
	}

	public void setTurtleImage(String image) {
		TurtleElement myTurtleElement = (TurtleElement) myView.getMyActiveWorkspace().getMyTurtleElement();
		
		StackPane myTurtleWrapper = myView.getMyActiveWorkspace().getMyTurtleWrapper();
		Canvas myTurtlePlayground = myView.getMyActiveWorkspace().getMyTurtlePlayground();

		ImageView newImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(image + ".jpg")));
		newImage.setFitWidth(Constants.TURTLE_ELEMENT_WIDTH);
		newImage.setFitHeight(Constants.TURTLE_ELEMENT_HEIGHT);
		Double oldX = myTurtleElement.getNode().getTranslateX();
		Double oldY = myTurtleElement.getNode().getTranslateY();
		((TurtleElement) myTurtleElement).setTurtleImage(newImage);
		myTurtleWrapper.getChildren().clear();
		myTurtleWrapper.getChildren().addAll(myTurtlePlayground, myTurtleElement.getNode());
		((TurtleElement) myTurtleElement).moveTurtleImage(oldX, oldY);
		((TurtleElement) myTurtleElement)
				.setTurtleOrientation(myActiveModel.getMyPlayground().getCurrentTurtle().getOrientation());

		myActiveModel.getMyPlayground().setCurrentTurtleShape(image);
	}

	public void setTurtleElement(PanelElement turtleElement) {
		myTransformer.setTurtleElement(turtleElement);
	}

	public void setBackgroundColor(Color color) {
		myView.getMyActiveWorkspace().getMyTurtleBackground()
				.setBackground(new Background(new BackgroundFill(color, Constants.CORNER_RADIUS, null)));
		myActiveModel.getMyPlayground().setBackgroundColor(color);
	}

	public void setPenColor(Color color) {
		myActiveModel.getMyPlayground().setCurrentPenColor(color);
	}

	public void setPenWidth(double value) {
		myActiveModel.getMyPlayground().setCurrentPenSize(value);
	}

	public void setLanguage(String language) {
		myActiveModel.setLanguage(language);
		myTransformer.setLanguage(language);
	}

	public void replaceVariable(String oldVar, String newVar) {
		myActiveModel.replaceVariable(oldVar, newVar);
	}

	public void replaceVariableValue(String name, String newVal) {
		myActiveModel.replaceVariableValue(name, newVal);
	}

	public void setAnimationSpeed(double speed) {
		if (myTimeline != null) {
			myTimeline.setRate(speed);
		}
	}

	public void setHelpMenu(boolean basic) {
		myView.showHelpScene(basic);
	}

	public void setPrimaryPane() {
		myView.showPrimaryScene();
	}

}
