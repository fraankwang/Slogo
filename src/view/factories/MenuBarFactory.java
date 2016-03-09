package view.factories;

import java.io.File;
import java.util.List;

import configuration.ConfigurationInfo;
import configuration.XMLParser;
import constants.Constants;
import controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.panelelements.PanelElement;

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
	 * 
	 * @return
	 */
	public MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = createFileMenu();
		Menu viewMenu = createViewMenu();
		Menu turtleMenu = createTurtleMenu();
		Menu configurationMenu = createConfigurationMenu();
		Menu helpMenu = createHelpMenu();

		menuBar.getMenus().addAll(fileMenu, configurationMenu, viewMenu, turtleMenu, helpMenu);
		return menuBar;

	}

	/**
	 * Creates all configuration-altering capabilities that involve files
	 * (saving and loading)
	 * 
	 * @return
	 */
	private Menu createFileMenu() {
		Menu fileMenu = new Menu(Constants.getSpecification("FileMenuOption"));
		MenuItem save = makeSaveButton();
		MenuItem load = makeLoadButton();
		fileMenu.getItems().addAll(save, load);
		return fileMenu;
		
	}

	/**
	 * Creates and links save configurations option to MainController
	 * 
	 * @return
	 */
	private MenuItem makeSaveButton() {
		MenuItem save = new MenuItem(Constants.getSpecification("SaveButtonDefaultText"));
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// new generator
				// myController.gatherallinfo
				// gatherallinfo method: creates XMLgenerator and generates
				// generate saved alert
				System.out.println("save pressed");
			}
		});
		return save;
		
	}

	/**
	 * Creates and links load configurations option to MainController
	 * 
	 * @return
	 */
	private MenuItem makeLoadButton() {
		MenuItem load = new MenuItem(Constants.getSpecification("LoadButtonDefaultText"));
		load.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				XMLParser parser = new XMLParser();
				FileChooser chooser = new FileChooser();
				FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
						Constants.getSpecification("XMLfileExtensionFilterDescription"),
						Constants.getSpecification("XMLfileExtensionFilter"));
				chooser.getExtensionFilters().addAll(extFilter);
				File chosenFile = chooser.showOpenDialog(myPrimaryStage);
				ConfigurationInfo configInfo = parser.parse(chosenFile);
				// readparsedinfo method: updates all configurations
				System.out.println(configInfo.getMyCommands().getUserCommandMap());
				myController.refreshDisplay();
				System.out.println("load pressed");
			}
		});
		
		return load;
		
	}

	/**
	 * Creates all modifiable configuration options
	 * 
	 * @return
	 */
	private Menu createConfigurationMenu() {
		Menu configurationMenu = new Menu(Constants.getSpecification("ConfigurationMenuOption"));

		Menu languageMenu = createLanguageMenu();
		CustomMenuItem backgroundColor = makeBackgroundColorPicker(Constants.DEFAULT_BACKGROUND_COLOR);
		CustomMenuItem animationSlider = makeAnimationRateSlider();
		configurationMenu.getItems().addAll(languageMenu, backgroundColor, animationSlider);

		return configurationMenu;
		
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
	 * Creates and links background color selection to MainController
	 * 
	 * @param defaultBackgroundColor
	 * @return
	 */
	private CustomMenuItem makeBackgroundColorPicker(Color defaultBackgroundColor) {
		VBox BackgroundColorWrapper = new VBox();
	
		Label BackgroundColorlabel = new Label(Constants.getSpecification("BackgroundColorPickerLabel"));
		BackgroundColorlabel.setTextFill(Color.BLACK);
	
		ColorPicker colorPicker = new ColorPicker(defaultBackgroundColor);
		BackgroundColorWrapper.getChildren().addAll(BackgroundColorlabel, colorPicker);
		CustomMenuItem penColor = new CustomMenuItem(BackgroundColorWrapper);
		penColor.setHideOnClick(false);
		penColor.setOnAction(e -> myController.setBackgroundColor(colorPicker.getValue()));
	
		return penColor;
	
	}

	/**
	 * Creates and links Animation speed to MainController
	 * 
	 * @return
	 */
	private CustomMenuItem makeAnimationRateSlider() {
		VBox sliderWrapper = new VBox();

		Label sliderLabel = new Label(Constants.getSpecification("AnimationSliderLabel"));
		sliderLabel.setTextFill(Color.BLACK);

		Slider slider = new Slider(Constants.ANIMATION_SLIDER_MIN, Constants.ANIMATION_SLIDER_MAX,
				Constants.DEFAULT_ANIMATION_SPEED);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setStyle("-fx-stroke: black;");
		slider.valueProperty().addListener(e -> myController.setAnimationSpeed(slider.getValue()));

		sliderWrapper.getChildren().addAll(sliderLabel, slider);
		CustomMenuItem sliderItem = new CustomMenuItem(sliderWrapper);
		sliderItem.setHideOnClick(false);
		return sliderItem;

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

		return item;

	}

	/**
	 * Creates all turtle-related menu options
	 * 
	 * @return
	 */
	private Menu createTurtleMenu() {
		Menu turtleMenu = new Menu(Constants.getSpecification("TurtleMenuOption"));

		CustomMenuItem penColor = makePenColorPicker(Constants.DEFAULT_PEN_COLOR);
		
		MenuItem resetTurtle = new MenuItem(Constants.getSpecification("ResetTurtleOption"));
		resetTurtle.setOnAction(e -> myController.resetTurtlePosition());
		MenuItem turtleImages = makeTurtleImages();
		CustomMenuItem penWidth = makePenWidthPicker();
		SeparatorMenuItem sep = new SeparatorMenuItem();
		SeparatorMenuItem sep2 = new SeparatorMenuItem();
		turtleMenu.getItems().addAll(penColor, penWidth, sep, resetTurtle, sep2, turtleImages);
		return turtleMenu;

	}

	/**
	 * Creates and links pen color selection to MainController
	 * 
	 * @param defaultPenColor
	 * @return
	 */
	private CustomMenuItem makePenColorPicker(Color defaultPenColor) {
		Label colorPickerLabel = new Label(Constants.getSpecification("PenColorPickerLabel"));
		colorPickerLabel.setTextFill(Color.BLACK);
		ColorPicker colorPicker = new ColorPicker(defaultPenColor);
		VBox wrapper = new VBox();
		wrapper.getChildren().addAll(colorPickerLabel, colorPicker);
		CustomMenuItem penColor = new CustomMenuItem(wrapper);
		penColor.setHideOnClick(false);
		penColor.setOnAction(e -> myController.setPenColor(colorPicker.getValue()));

		return penColor;

	}

	/**
	 * Creates and links pen width to MainController
	 * 
	 * @return
	 */
	private CustomMenuItem makePenWidthPicker() {
		VBox sliderWrapper = new VBox();

		Label sliderLabel = new Label(Constants.getSpecification("PenWidthLabel"));
		sliderLabel.setTextFill(Color.BLACK);

		Slider slider = new Slider(Constants.PEN_WIDTH_MIN, Constants.PEN_WIDTH_MAX,
				Constants.DEFAULT_TURTLE_PEN_WIDTH);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setStyle("-fx-stroke: black;");
		slider.valueProperty().addListener(e -> myController.setPenWidth(slider.getValue()));

		sliderWrapper.getChildren().addAll(sliderLabel, slider);
		CustomMenuItem sliderItem = new CustomMenuItem(sliderWrapper);
		sliderItem.setHideOnClick(false);
		return sliderItem;
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
					myController.setTurtleImage(item.getText());
				}
			});
		}

		return turtleImages;

	}

	/**
	 * Takes @param image and creates a CheckMenuItem with the name and
	 * corresponding image as the elements
	 * 
	 * @return
	 */
	private MenuItem createTurtleImageMenuItem(String image) {
		MenuItem turtleImage = new MenuItem(image);
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
		iv.setFitHeight(Constants.MENU_TURTLE_IMAGE_SIZE);
		iv.setFitWidth(Constants.MENU_TURTLE_IMAGE_SIZE);
		return iv;

	}

	/**
	 * Creates help menu button
	 * 
	 * @return
	 */
	private Menu createHelpMenu() {
		Menu helpMenu = new Menu(Constants.getSpecification("HelpMenuOption"));
		MenuItem basicHelpItem = new MenuItem(Constants.getSpecification("BasicCommandsHelpOption"));
		MenuItem advancedHelpItem = new MenuItem(Constants.getSpecification("AdvancedCommandsHelpOption"));
		basicHelpItem.setOnAction(e -> myController.setHelpMenu(true));
		advancedHelpItem.setOnAction(e -> myController.setHelpMenu(false));

		helpMenu.getItems().addAll(basicHelpItem, advancedHelpItem);
		return helpMenu;

	}

	/**
	 * Deselects all CheckMenuItems in given list
	 * 
	 * @param lists
	 */
	private void deselectAll(List<MenuItem> list) {
		for (MenuItem item : list) {
			((CheckMenuItem) item).setSelected(false);
		}

	}
}
