//package view;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import constants.Constants;
//import constants.StringImageCell;
//import controller.MainController;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.scene.Node;
//import javafx.scene.control.CheckMenuItem;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Control;
//import javafx.scene.control.CustomMenuItem;
//import javafx.scene.control.Label;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.Slider;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.scene.text.TextAlignment;
//
//public class Toolbar {
//
//	private static final int SCENE_WIDTH = Constants.SCENE_WIDTH;
//	
//	private static final int MENU_BAR_HEIGHT = Constants.MENU_BAR_HEIGHT;
//	private static final int TOOLBAR_INSET_HORIZONTAL = Constants.TOOLBAR_INSET_HORIZONTAL;
//	private static final int TOOLBAR_INSET_HORIZONTAL_LABEL = Constants.TOOLBAR_INSET_HORIZONTAL_LABEL;
//	private static final int TOOLBAR_INSET_VERTICAL = Constants.TOOLBAR_INSET_VERTICAL;
//	private static final int TOOLBAR_LABEL_FONT_SIZE = Constants.TOOLBAR_LABEL_FONT_SIZE;
//	
//	private MainController myController;
//	
//	public Toolbar (MainController controller) {
//		myController = controller;
//	}
//	
//	
//	
//	
//
//	/**
//	 * Helper UI-creating function that adds tool bar elements and links them to
//	 * myController
//	 * 
//	 * @return formatted HBox
//	 */
//	public HBox createToolBar() {
//		HBox toolbar = new HBox();
//		toolbar.setPrefHeight(MENU_BAR_HEIGHT);
//		toolbar.setPrefWidth(SCENE_WIDTH);
//		toolbar.setBackground(new Background(new BackgroundFill(Constants.DEFAULT_TOOLBAR_COLOR, Constants.CORNER_RADIUS, null)));
//
//		Label penColorPickerLabel = makeLabel("PenColorPickerLabel");
//		ColorPicker penColorPicker = new ColorPicker(Constants.DEFAULT_PEN_COLOR);
//		penColorPicker.setOnAction(e -> myController.setPenColor(penColorPicker.getValue()));
//
//		Label backgroundColorPickerLabel = makeLabel("BackgroundPickerLabel");
//		ColorPicker backgroundColorPicker = new ColorPicker(Constants.DEFAULT_BACKGROUND_COLOR);
//		backgroundColorPicker.setOnAction(e -> myController.setBackgroundColor(backgroundColorPicker.getValue()));
//
//		Label turtleImageChooserLabel = makeLabel("ImagePickerLabel");
//		ComboBox<String> turtleImageChooser = makeComboBox(Constants.getTurtleImages(), true);
//		turtleImageChooser.setOnAction(e -> myController.setTurtleImage(turtleImageChooser.getValue()));
//
//		Label languageChooserLabel = makeLabel("LanguagePickerLabel");
//		ComboBox<String> languageChooser = makeComboBox(Constants.getLanguages(), false);
//		languageChooser.setOnAction(e -> myController.setLanguage(languageChooser.getValue()));
//
//		List<Node> labels = Arrays.asList(penColorPickerLabel, backgroundColorPickerLabel, turtleImageChooserLabel,
//				languageChooserLabel);
//
//		List<Node> controls = Arrays.asList(penColorPicker, backgroundColorPicker, turtleImageChooser,
//				languageChooser);
//
//		List<Node> allElements = new ArrayList<Node>(labels);
//		allElements.addAll(controls);
//		setMargins(allElements);
//		
//		for (int i = 0; i < labels.size(); i++) {
//			toolbar.getChildren().addAll(labels.get(i), controls.get(i));
//		}
//
//		return toolbar;
//
//		// // setting animation speed
//		// Slider slider = new Slider(0, 100.0, 1.0);
//		// slider.valueProperty().addListener(e ->
//		// myController.setAnimationSpeed(slider.getValue()));
//
//		
//		
//		
//		
//	}
//
//	/**
//	 * Takes @param items, checks what type of Object they are, and applies relevant insets
//	 */
//	private void setMargins(List<Node> items) {
//		Insets labelInsets = new Insets(TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL,
//				TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL);
//
//		Insets controlInsets = new Insets(TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL, TOOLBAR_INSET_HORIZONTAL,
//				TOOLBAR_INSET_VERTICAL);
//		
//		for (Node item: items) {
//			if (item instanceof Label) {
//				HBox.setMargin(item, labelInsets);
//			}
//			else if (item instanceof Control) {
//				HBox.setMargin(item, controlInsets);
//			}
//		}
//		
//	}
//
//	/**
//	 * Creates a ComboBox and populates the options with given list of Strings.
//	 * If @param images is true, use StringImageCell so image option does not
//	 * disappear upon clicking
//	 * 
//	 * @param list
//	 * @param images
//	 * @return
//	 */
//	private ComboBox<String> makeComboBox(List<String> list, boolean images) {
//		ComboBox<String> chooser = new ComboBox<String>();
//		chooser.getItems().addAll(list);
//		chooser.setValue(list.get(0));
//		if (images) {
//			chooser.setCellFactory(listview -> new StringImageCell());
//			chooser.setButtonCell(new StringImageCell());
//
//		}
//
//		return chooser;
//
//	}
//
//	/**
//	 * Creates uniformly formatted label with title set as the text 
//	 * @param title
//	 * @return
//	 */
//	private Label makeLabel(String title) {
//		Label label = new Label();
//		label.setText(Constants.getSpecification(title));
//		label.setTextFill(Color.WHITE);
//		label.setTextAlignment(TextAlignment.CENTER);
//		label.setFont(new Font(TOOLBAR_LABEL_FONT_SIZE));
//		return label;
//
//	}
//	
//	
//	
//	
//
//}
