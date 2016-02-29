package view;

import java.util.List;

import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarFactory {
	
	private MainController myController;

	public MenuBarFactory(MainController controller) {
		myController = controller;
		
	}
	
	/**
	 * Creates menu bar of all modifiable aspects of Slogo
	 * @return
	 */
	public MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu languageMenu = createLanguageMenu();
		Menu viewMenu = createViewMenu();
		Menu turtleMenu = createTurtleMenu(); //pen color, image, add new image
		Menu configurationMenu = createConfigurationMenu(); //animation speed, background color, language
		Menu helpMenu = createHelpMenu();
		
		menuBar.getMenus().addAll(languageMenu, viewMenu, configurationMenu, turtleMenu, helpMenu);
		return menuBar;

//		CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
//		customMenuItem.setHideOnClick(false);
//		openFile.setGraphic(new ImageView(new Image("basic.jpg")));
//
//		CustomMenuItem menuItemColor = new CustomMenuItem(new ColorPicker());
//		menuItemColor.setHideOnClick(false);

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
				} 
				else {
					item.setSelected(true);
				}

				element.toggleDisplay();
			}
		});

		return item;
		
	}

	/**
	 * Creates a menu to change language options and initializes default language to English
	 * @return
	 */
	private Menu createLanguageMenu() {
		Menu languages = new Menu(Constants.getSpecification("LanguageMenuOption"));
		List<String> allOptions = Constants.getLanguages();
		
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
	 * Creates all turtle-related menu options
	 * @return
	 */
	private Menu createTurtleMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates all modifiable configuration options 
	 * @return
	 */
	private Menu createConfigurationMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates help menu button
	 * @return
	 */
	private Menu createHelpMenu() {
		// TODO Auto-generated method stub
		return null;
	}

}
