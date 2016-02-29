/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package constants;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;

import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_RESOURCE_FILE = "Specifications";
	private static final String DEFAULT_LANGUAGES = "languages/";
	// private static final String DEFAULT_LANGUAGE =
	// DEFAULT_LANGUAGES+"English";
	private static final String DEFAULT_ACTIONS = "possibleactions";
	private static final String DEFAULT_PARAMS = "numberofparams";

	private static final ResourceBundle SPECIFICATIONS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);
	// private static final ResourceBundle COMMANDS = ResourceBundle
	// .getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE);
	private static final ResourceBundle ACTIONS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_ACTIONS);

	private static final ResourceBundle NUMBER_OF_PARAMS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_PARAMS);

	private static List<String> TURTLE_IMAGES = Arrays.asList("basic", "duvall");
	private static final List<String> LANGUAGES = Arrays.asList("English", "Spanish", "Chinese", "French", "German",
			"Italian", "Portuguese", "Russian");

	public static final String OPEN_BRACKET = "[";
	public static final String CLOSE_BRACKET = "]";

	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final Paint DEFAULT_TOOLBAR_COLOR = Color.BLACK;

	public static final int SCENE_WIDTH = 1000;
	public static final int SCENE_HEIGHT = 750;

	public static final int LEFT_COLUMN_WIDTH = 700;
	public static final int PLAYGROUND_HEIGHT = 550;
	public static final int RIGHT_COLUMN_WIDTH = 300;
	public static final int RIGHT_COLUMN_ELEMENT_HEIGHT = 145;

	public static final int TOOLBAR_HEIGHT = 50;
	public static final int TOOLBAR_INSET_HORIZONTAL = 10;
	public static final int TOOLBAR_INSET_HORIZONTAL_LABEL = 16;
	public static final int TOOLBAR_INSET_VERTICAL = 5;
	public static final int TOOLBAR_LABEL_FONT_SIZE = 11;
	public static final int TOOLBAR_IMAGE_SIZE = 20;

	public static final int TEXTAREA_HEIGHT = 125;
	public static final int RUN_BUTTON_HEIGHT = 25;
	public static final double CLEAR_BUTTON_HEIGHT = 25;

	public static final int TEXTFIELD_HEIGHT = SCENE_HEIGHT - TOOLBAR_HEIGHT - PLAYGROUND_HEIGHT;

	public static final CornerRadii CORNER_RADIUS = new CornerRadii(10.0);
	public static final double ELEMENT_INSET_HORIZONTAL = 7;
	public static final double ELEMENT_INSET_VERTICAL = 0;

	public static String getSpecification(String name) {
		return SPECIFICATIONS.getString(name);
	}

	public static String getCommand(String language, String command) throws Exception {
		ResourceBundle COMMANDS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGES + language);
		for (String e : COMMANDS.keySet()) {
			for (String f : COMMANDS.getString(e).split("\\|")) {
				// System.out.println(f);
				if (f.equals(command)) {
					return e;
				}
			}
		}
		throw new ParseException();
	}

	public static int getNumberParams(String superclass) {
		return Integer.parseInt(NUMBER_OF_PARAMS.getString(superclass));
	}

	public static String getAction(String action) {
		return ACTIONS.getString(action);
	}

	public static List<String> getTurtleImages() {
		return TURTLE_IMAGES;
	}

	public static List<String> getLanguages() {
		return LANGUAGES;
	}
}
