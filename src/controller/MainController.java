// This entire file is part of my masterpiece.
// Frank Wang

/**
 * This class acts as the controller within the over-arching MVC of our entire project.
 * I wrote this entire class as well as everything in the ModelTransformer class.
 * 
 * 1. This class is the cornerstone of our entire project. Without it, the front-end would 
 * stand as an empty shell while the back-end's model information would not be displayable ever. 
 * 2. The MainController contains references to the MainView and the active MainModel, 
 * as well as a map of Models. The MainModels and Workspaces correspond to each other, but
 * the map of Workspaces along with the active Workspace are managed by the MainView class instead.
 * 3. This class has been extensively refactored to be cleaner but comprehensive enough to
 * fully link the front-end and back-end.
 * 4. The primary method is the executeCommand method, which has 2 parts: tell the MainModel to 
 * update itself given the parameter command given, and then to pull the updated model
 * information and pass it to the ModelTransformer which takes care of all the transformations.
 * 5. All the transformations are encapsulated in the ModelTransformation, while all the 
 * PanelElements and UI-related components are encapsulated within MainView's active Workspace, 
 * and all the model information is encapsulated within each instance of MainModel. Therefore
 * the MainController deals with only the active Workspace and active Models. 
 * 6. The MainController also has control over setting the active MainModel, though 
 * this is called from the MainView by adding a listener to the TabPane's selection model.
 * 7. The MainController is also responsible for gathering and updating the configuration-related
 * information, as it is the only class that has global access to all the necessary configuration information
 * 
 */

package controller;

import java.util.*;
import configuration.ConfigurationInfo;
import constants.Constants;
import controller.ModelTransformer;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MainModel;
import view.MainView;
import view.Workspace;
import view.panelelements.PanelElement;

public class MainController {

	private MainView myView;
	private MainModel myActiveModel;
	private Map<Integer, MainModel> myModels;
	private ModelTransformer myTransformer;

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

	/**
	 * Deletes stored previously run commands and moves turtle position back to
	 * start
	 */
	public void resetTurtlePosition() {
		myTransformer.resetTurtlePosition();
		myTransformer.transformTurtleGraphics(myActiveModel.getMyPlayground());

	}

	/**
	 * Grabs current turtle information and displays it in given TextArea
	 * 
	 * @param textArea
	 */
	public void displayTurtleInfo(TextArea textArea) {
		myTransformer.displayTurtleInfo(textArea);
	}

	// =========================================================================
	// Modifiers, Getters, and Setters
	// =========================================================================

	public MainView getMyView() {
		return myView;
	}

	public void setHelpMenu(boolean basic) {
		myView.showHelpScene(basic);
	}

	public void setPrimaryPane() {
		myView.showPrimaryScene();
	}

	public void setMyActiveModel(int newTabIndex) {
		myActiveModel = myModels.get(newTabIndex);
	}

	public MainModel getMyActiveModel() {
		return myActiveModel;
	}

	public List<PanelElement> getViewableElements() {
		return myView.getViewableElements();
	}

	public void setTurtleImage(String image) {
		myTransformer.setTurtleImage(image);
	}

	public void setTurtleElement(PanelElement turtleElement) {
		myTransformer.setTurtleElement(turtleElement);
	}

	public void setBackgroundColor(Color color) {
		myView.getMyActiveWorkspace().setTurtleBackground(color);
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

}
