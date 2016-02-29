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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuBarFactory {

	private static final int TOOLBAR_IMAGE_SIZE = Constants.TOOLBAR_IMAGE_SIZE;

	private MainController myController;
	private Stage myPrimaryStage;

	private Menu turtleImages;

	public MenuBarFactory(MainController controller, Stage stage) {
		myController = controller;
		myPrimaryStage = stage;

	}

	/**
	 * Creates menu bar of all modifiable aspects of Slogo
	 * 
	 * @return
	 */
	public MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu languageMenu = createLanguageMenu();
		Menu viewMenu = createViewMenu();
		Menu turtleMenu = createTurtleMenu();
		Menu configurationMenu = createConfigurationMenu(); // animation speed,
															// background color,
															// language
		Menu helpMenu = createHelpMenu();

		menuBar.getMenus().addAll(languageMenu, viewMenu, turtleMenu);
		// menuBar.getMenus().addAll(languageMenu, viewMenu, configurationMenu,
		// turtleMenu, helpMenu);
		return menuBar;

	}

	/**
	 * Goes through all viewable elements and creates checkable menu items that
	 * toggle display
	 * 
	 * @return
	 */
	private Menu createViewMenu() {
		Menu viewMenu = new Menu(Constants.getSpecification("ViewMenuOption"));
		List<PanelElement> viewableElements = myController.getViewableElements();

		for (PanelElement element : viewableElements) {
			viewMenu.getItems().add(createViewMenuElement(element));

		}
		return viewMenu;
	}

	/**
	 * Helper method that translates PanelElement to a checkable menu item
	 * 
	 * @param element
	 * @return
	 */
	private CheckMenuItem createViewMenuElement(PanelElement element) {
		CheckMenuItem item = new CheckMenuItem();
		item.setText("Show " + element.getName());
		item.selectedProperty().setValue(true);
		element.getNode().visibleProperty().bindBidirectional(item.selectedProperty());
//		item.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {	
//				System.out.println(item.selectedProperty().getValue());
//				if (item.selectedProperty().getValue() == false) {
//					item.selectedProperty().setValue(true);
//				} 
//				else if (item.selectedProperty().getValue() == true){
//					item.selectedProperty().setValue(false);
//				}
//				
//				element.toggleDisplay();
//			}
//		});

		return item;

		
	}

	/**
	 * Creates a menu to change language options and initializes default
	 * language to English
	 * 
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
	 * 
	 * @return
	 */
	private Menu createTurtleMenu() {
		Menu turtleMenu = new Menu(Constants.getSpecification("TurtleMenuOption"));
		// pen color, image, add new image

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
	 * 
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
	 * 
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
			turtleImages.getItems().add(createTurtleImageMenuItem(image));
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
	 * Takes @param image and creates a CheckMenuItem with the name and corresponding image as the elements
	 * @return
	 */
	private CheckMenuItem createTurtleImageMenuItem(String image) {
		CheckMenuItem turtleImage = new CheckMenuItem(image);
		ImageView iv = createTurtleMenuGraphic(image);
		turtleImage.setGraphic(iv);
		return turtleImage;
	}

	/**
	 * Loads @param image as a .jpg file from src folder and sizes it
	 * 
	 * @return
	 */
	private ImageView createTurtleMenuGraphic(String image) {
		String path = image + Constants.getSpecification("AllowedUploadedImageTypes");
		ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
		iv.setFitHeight(TOOLBAR_IMAGE_SIZE);
		iv.setFitWidth(TOOLBAR_IMAGE_SIZE);
		return iv;
	}

	/**
	 * Allows user to upload new image for Turtle to take
	 * 
	 * @return
	 */
	private MenuItem makeUploadNewOption() {
		MenuItem uploadNew = new MenuItem(Constants.getSpecification("UploadNewTurtleImageOption"));
		uploadNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				FileChooser fileChooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						Constants.getSpecification("AllowedUploadedImageTypesFilterDescription"),
						Constants.getSpecification("AllowedUploadedImageTypesFilter"));
				fileChooser.getExtensionFilters().addAll(extFilter);
				File chosenFile = fileChooser.showOpenDialog(myPrimaryStage);
				// ON HOLD UNTIL EXTENSION
				// SAVE FILE (only JPG to src/images)
				// call createTurtleImageMenuItem() and add it to turtleImages
			}
		});

		return uploadNew;
	}

	/**
	 * Creates all modifiable configuration options
	 * 
	 * @return
	 */
	private Menu createConfigurationMenu() {
		Menu configMenu = new Menu(Constants.getSpecification("ConfigurationMenuOption"));
		return null;
		// CustomMenuItem customMenuItem = new CustomMenuItem(new Slider());
		// customMenuItem.setHideOnClick(false);
	}

	/**
	 * Creates help menu button
	 * 
	 * @return
	 */
	private Menu createHelpMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Unselects all CheckMenuItems in given list
	 * 
	 * @param lists
	 */
	private void deselectAll(List<MenuItem> list) {
		for (MenuItem item : list) {
			((CheckMenuItem) item).setSelected(false);
		}
	}
}
