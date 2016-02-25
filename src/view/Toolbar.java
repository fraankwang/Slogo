package view;

import java.util.Arrays;
import java.util.List;

import constants.Constants;
import constants.StringImageCell;
import controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Toolbar {

	private final int SCENE_WIDTH = 1000;
	
	private final int TOOLBAR_HEIGHT = 50;
	private final int TOOLBAR_INSET_HORIZONTAL = 10;
	private final int TOOLBAR_INSET_HORIZONTAL_LABEL = 16;
	private final int TOOLBAR_INSET_VERTICAL = 5;
	private final int TOOLBAR_LABEL_FONT_SIZE = 11;
	
	private MainController myController;
	
	public Toolbar (MainController controller) {
		myController = controller;
	}
	
	/**
	 * Helper UI-creating function that adds tool bar elements and links them to
	 * myController
	 * 
	 * @return formatted HBox
	 */
	public HBox createToolBar() {
		HBox toolbar = new HBox();
		toolbar.setPrefHeight(TOOLBAR_HEIGHT);
		toolbar.setPrefWidth(SCENE_WIDTH);
		toolbar.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		Label penColorPickerLabel = makeLabel("PenColorPickerLabel");
		ColorPicker penColorPicker = new ColorPicker(Color.BLACK);
		penColorPicker.setOnAction(e -> myController.setPenColor(penColorPicker.getValue()));

		Label backgroundColorPickerLabel = makeLabel("BackgroundPickerLabel");
		ColorPicker backgroundColorPicker = new ColorPicker(Color.WHITE);
		backgroundColorPicker.setOnAction(e -> myController.setBackgroundColor(backgroundColorPicker.getValue()));

		Label turtleImageChooserLabel = makeLabel("ImagePickerLabel");
		ComboBox<String> turtleImageChooser = makeComboBox(Constants.getTurtleImages(), true);
		turtleImageChooser.setOnAction(e -> myController.setTurtleImage(turtleImageChooser.getValue()));

		Label languageChooserLabel = makeLabel("LanguagePickerLabel");
		ComboBox<String> languageChooser = makeComboBox(Constants.getLanguages(), false);
		languageChooser.setOnAction(e -> myController.setLanguage(languageChooser.getValue()));

		List<Label> labels = Arrays.asList(penColorPickerLabel, backgroundColorPickerLabel, turtleImageChooserLabel,
				languageChooserLabel);
		setMarginsLabel(labels);

		List<Control> controls = Arrays.asList(penColorPicker, backgroundColorPicker, turtleImageChooser,
				languageChooser);
		setMarginsControl(controls);

		for (int i = 0; i < labels.size(); i++) {
			toolbar.getChildren().addAll(labels.get(i), controls.get(i));
		}

		return toolbar;

		// // setting animation speed
		// Slider slider = new Slider(0, 100.0, 1.0);
		// slider.valueProperty().addListener(e ->
		// myController.setAnimationSpeed(slider.getValue()));

	}

	/**
	 * Loops through all tool bar labels and formats margins
	 * 
	 * @param labels
	 */
	private void setMarginsLabel(List<Label> labels) {
		Insets insets = new Insets(TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL,
				TOOLBAR_INSET_HORIZONTAL_LABEL, TOOLBAR_INSET_VERTICAL);

		for (Label label : labels) {
			HBox.setMargin(label, insets);
		}

	}

	/**
	 * Loops through all tool bar controls and formats margins
	 * 
	 * @param labels
	 */
	private void setMarginsControl(List<Control> controls) {
		Insets insets = new Insets(TOOLBAR_INSET_HORIZONTAL, TOOLBAR_INSET_VERTICAL, TOOLBAR_INSET_HORIZONTAL,
				TOOLBAR_INSET_VERTICAL);

		for (Control control : controls) {
			HBox.setMargin(control, insets);
		}

	}

	/**
	 * Creates a ComboBox and populates the options with given list of Strings.
	 * If @param images is true, use StringImageCell so image option does not
	 * disappear upon clicking
	 * 
	 * @param list
	 * @param images
	 * @return
	 */
	private ComboBox<String> makeComboBox(List<String> list, boolean images) {
		ComboBox<String> chooser = new ComboBox<String>();
		chooser.getItems().addAll(list);
		chooser.setValue(list.get(0));
		if (images) {
			chooser.setCellFactory(listview -> new StringImageCell());
			chooser.setButtonCell(new StringImageCell());

		}

		return chooser;

	}

	/**
	 * Creates uniformly formatted label with title set as the text 
	 * @param title
	 * @return
	 */
	private Label makeLabel(String title) {
		Label label = new Label();
		label.setText(Constants.getSpecification(title));
		label.setTextFill(Color.WHITE);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setFont(new Font(TOOLBAR_LABEL_FONT_SIZE));
		return label;

	}
}
