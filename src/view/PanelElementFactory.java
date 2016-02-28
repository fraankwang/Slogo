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
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;

public class PanelElementFactory {
	
	private static final int LEFT_COLUMN_WIDTH = Constants.LEFT_COLUMN_WIDTH;
	private static final int PLAYGROUND_HEIGHT = Constants.PLAYGROUND_HEIGHT;
	private static final int RIGHT_COLUMN_WIDTH = Constants.RIGHT_COLUMN_WIDTH;
	private static final int RIGHT_COLUMN_ELEMENT_HEIGHT = Constants.RIGHT_COLUMN_ELEMENT_HEIGHT;
	
	private MainController myController;

	
	public PanelElementFactory(MainController myController) {
		myController = myController;
	}

	
	
	
	public MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu languageMenu = createLanguageMenu();
		Menu viewMenu = createViewMenu();
		Menu turtleMenu = createTurtleMenu(); //pen color, image, add new image
		Menu configurationMenu = createConfigurationMenu(); //animation speed, background color, language
		Menu helpMenu = createHelpMenu();
		
		
		
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
//		menu1.getItems().addAll(openFile, customMenuItem);
		CustomMenuItem menuItemColor = new CustomMenuItem(new ColorPicker());
		menuItemColor.setHideOnClick(false);
//		menu1.getItems().add(menuItemColor);
		
		final Menu menu2 = new Menu("Options");
		Menu menu3 = new Menu("Help");

		menuBar.getMenus().addAll(languageMenu, viewMenu);
		return menuBar;
	}
	
	
	
	/**
	 * Goes through all viewable elements and creates checkable menu items that toggle display
	 * @return
	 */
	private Menu createViewMenu() {
		Menu viewMenu = new Menu("View");
		List<PanelElement> viewableElements = myController.getViewableElements();
		
		for (PanelElement element : viewableElements) {
			viewMenu.getItems().add(createViewMenuElement(element));
			
		}
		return viewMenu;
	}

	/**
	 * Helper method that translates PanelElement to a checkable menu item
	 * @param element
	 * @return
	 */
	private CheckMenuItem createViewMenuElement(PanelElement element) {
		CheckMenuItem item = new CheckMenuItem();
		item.setText(element.getName());
		item.setSelected(true);
		item.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	if (item.isSelected()) {
		    		item.setSelected(false);
		    		myController.toggleDisplay(element);
		    	}
		    	else {
		    		item.setSelected(true);
		    		myController.toggleDisplay(element);
		    	}
		    	
		    }
		});
		
		return item;
		
	}

	/**
	 * Creates a menu to change language options and initializes default language to English
	 * @return
	 */
	private Menu createLanguageMenu() {
		Menu languages = new Menu("Language");
		List<String> allOptions = Constants.getLanguages();
		myController.setLanguage(allOptions.get(0));
		
		for (String language : allOptions) {
			MenuItem item = createLanguageMenuElement(language);
			languages.getItems().add(item);
		}
		
		return languages;
		
	}

	/**
	 * Helper function for creating language menu
	 * @return
	 */
	private MenuItem createLanguageMenuElement(String language) {
		
		CheckMenuItem item = new CheckMenuItem(language);
		item.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				item.setSelected(true);
				myController.setLanguage(language);
			}
		});
		
		return item;
		
	}
	
	
	/**
	 * @return formatted StackPane which contains turtle Canvas
	 */
	private StackPane makePlayground() {
		StackPane myTurtleBackground = new StackPane();
		myTurtleBackground.setPrefHeight(PLAYGROUND_HEIGHT);
		myTurtleBackground.setPrefWidth(LEFT_COLUMN_WIDTH);
		myTurtleBackground.setBackground(
				new Background(new BackgroundFill(Constants.DEFAULT_BACKGROUND_COLOR, Constants.CORNER_RADIUS, null)));
		Canvas playground = new Canvas(LEFT_COLUMN_WIDTH, PLAYGROUND_HEIGHT);
		setTurtleBox(playground.getGraphicsContext2D());

		myTurtleBackground.getChildren().add(playground);
		return myTurtleBackground;
	}

	/**
	 * @return TextArea that sets myController to fully run the command and
	 *         refresh the display
	 */
	private TextArea makeTextArea() {
		TextArea textArea = new TextArea();
		textArea.setPromptText(Constants.getSpecification("TextAreaDefaultText"));
		textArea.setPrefHeight(Constants.TEXTAREA_HEIGHT);
		return textArea;
	}
	
	private Button makeRunButton(TextArea ta) {
		Button runButton = new Button(Constants.getSpecification("RunButtonDefaultText"));
		runButton.setPrefHeight(Constants.RUN_BUTTON_HEIGHT);
		runButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myController.executeCommand(ta.getText());
			}
		});
		return runButton;
	}
	
	
	// =========================================================================
	// Getters and Setters
	// =========================================================================	
	
}
