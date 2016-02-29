package view;


import java.io.File;
import java.util.List;


import constants.Constants;
import constants.StringImageCell;
import controller.MainController;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuBarFactory {
	
	private MainController myController;
	private Stage myPrimaryStage;
	
	private Menu turtleImages;

	public MenuBarFactory(MainController controller, Stage stage) {
		myController = controller;
		myPrimaryStage = stage;
		
	}
	
	/**
	 * Creates menu bar of all modifiable aspects of Slogo
	 * @return
	 */
	public MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu languageMenu = createLanguageMenu();
		Menu viewMenu = createViewMenu();
		Menu turtleMenu = createTurtleMenu(); 
		Menu configurationMenu = createConfigurationMenu(); //animation speed, background color, language
		Menu helpMenu = createHelpMenu();
		
		menuBar.getMenus().addAll(languageMenu, viewMenu, turtleMenu);
//		menuBar.getMenus().addAll(languageMenu, viewMenu, configurationMenu, turtleMenu, helpMenu);
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
		item.setText("Show " + element.getName());
		item.setSelected(true);
		item.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				if (item.isSelected() == false) {
					item.setSelected(true);
					item.selectedProperty().setValue(true);
				} 
				else if (item.isSelected() == true){
					item.setSelected(false);
					item.selectedProperty().setValue(false);
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
			MenuItem item = new CheckMenuItem(language);
			languages.getItems().add(item);
		}
		
		((CheckMenuItem) languages.getItems().get(0)).setSelected(true);
		
		for (MenuItem item : languages.getItems()) {
			item.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					deselectAll(languages.getItems());
					((CheckMenuItem) item).setSelected(true);
					myController.setLanguage(item.getText());
					
				}
			});
		}
		return languages;
		
	}
	
	/**
	 * Creates all turtle-related menu options
	 * @return
	 */
	private Menu createTurtleMenu() {
		Menu turtleMenu = new Menu(Constants.getSpecification("TurtleMenuOption"));
		//pen color, image, add new image

		
		CustomMenuItem penColor = makePenColorPicker(Constants.DEFAULT_PEN_COLOR);
		CustomMenuItem backgroundColor = makeBackgroundColorPicker(Constants.DEFAULT_BACKGROUND_COLOR);
		MenuItem turtleImages = makeTurtleImages();
		MenuItem uploadNew = makeUploadNewOption();

		SeparatorMenuItem sep = new SeparatorMenuItem();
		turtleMenu.getItems().addAll(penColor, backgroundColor, sep, turtleImages, uploadNew);
		return turtleMenu;
	}

	/**
	 * Creates and links pen color selection to MainController
	 * @param defaultPenColor
	 * @return
	 */
	private CustomMenuItem makePenColorPicker(Color defaultPenColor) {
		Label label = new Label(Constants.getSpecification("PenColorPickerLabel"));
		label.setTextFill(Color.BLACK);
		ColorPicker colorPicker = new ColorPicker(defaultPenColor);
		VBox wrapper = new VBox();
		wrapper.getChildren().addAll(label, colorPicker);
		CustomMenuItem penColor = new CustomMenuItem(wrapper);
		penColor.setHideOnClick(false);
		penColor.setOnAction(e -> myController.setPenColor(colorPicker.getValue()));
		
		return penColor;
	}

	/**
	 * Creates and links background color selection to MainController
	 * @param defaultBackgroundColor
	 * @return
	 */
	private CustomMenuItem makeBackgroundColorPicker(Color defaultBackgroundColor) {
		Label label = new Label(Constants.getSpecification("BackgroundColorPickerLabel"));
		label.setTextFill(Color.BLACK);
		ColorPicker colorPicker = new ColorPicker(defaultBackgroundColor);
		VBox wrapper = new VBox();
		wrapper.getChildren().addAll(label, colorPicker);
		CustomMenuItem penColor = new CustomMenuItem(wrapper);
		penColor.setHideOnClick(false);
		penColor.setOnAction(e -> myController.setBackgroundColor(colorPicker.getValue()));
		
		return penColor;
	}

	/**
	 * @return selection of names of images for turtle
	 */
	private MenuItem makeTurtleImages() {
		turtleImages = new Menu(Constants.getSpecification("TurtleImagesOption"));
		
		for (String image : Constants.getTurtleImages()) {
			CheckMenuItem turtleImage = new CheckMenuItem(image);
			turtleImages.getItems().add(turtleImage);
		}

		for (MenuItem item : turtleImages.getItems()) {
			item.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					deselectAll(turtleImages.getItems());
					((CheckMenuItem) item).setSelected(true);
					myController.setTurtleImage(item.getText());
					
				}
			});
		}
		
		return turtleImages;
				
	}

	/**
	 * Allows user to upload new image for Turtle to take
	 * @return
	 */
	private MenuItem makeUploadNewOption() {
		MenuItem uploadNew = new MenuItem(Constants.getSpecification("UploadNewTurtleImageOption"));
		uploadNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
		        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
	            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
	            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		        File chosenFile = fileChooser.showOpenDialog(myPrimaryStage);
		        // ON HOLD UNTIL EXTENSION
			}
		});
		
		return uploadNew;
	}
	
	
	
	/**
	 * Creates all modifiable configuration options 
	 * @return
	 */
	private Menu createConfigurationMenu() {
		// TODO Auto-generated method stub
		return null;
//		CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
//		customMenuItem.setHideOnClick(false);
	}

	/**
	 * Creates help menu button
	 * @return
	 */
	private Menu createHelpMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Unselects all CheckMenuItems in given list
	 * @param lists
	 */
	private void deselectAll(List<MenuItem> list) {
		for (MenuItem item : list) {
			((CheckMenuItem) item).setSelected(false);
		}
	}
}
