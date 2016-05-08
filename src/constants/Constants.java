/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package constants;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import exceptions.ParseException;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_RESOURCE_FILE = "Specifications";
	private static final String DEFAULT_LANGUAGES = "languages/";

	private static final String DEFAULT_ACTIONS = "possibleactions";
	private static final String DEFAULT_PARAMS = "numberofparams";

	private static final String DEFAULT_ACTIONSUPERCLASS = "ActionSuperClass";
	private static final String DEFAULT_SHAPEMAP = "shapemap";

	private static final ResourceBundle SPECIFICATIONS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);
	private static final ResourceBundle ACTIONS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_ACTIONS);

	private static final ResourceBundle NUMBER_OF_PARAMS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_PARAMS);

	private static final List<String> DEFAULT_TURTLE_IMAGES = Arrays.asList("basic", "duvall");
	private static final List<String> LANGUAGES = Arrays.asList("English", "Spanish", "Chinese", "French", "German",
			"Italian", "Portuguese", "Russian");

	public static final String OPEN_BRACKET = "[";
	public static final String CLOSE_BRACKET = "]";
	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String TOO_FEW_PARAMETERS_ERROR = "Too few parameters";
	public static final String HASHTAG = "#";
	public static final String NEWLINE = "\\s";
	public static final String WHITESPACE = "\\n";
	public static final String SPACE = " ";
	public static final String TOO_MANY_PARAMETERS_ERROR = "Too many parameters";
	public static final String SYNTAX_ERROR = "Incorrect command syntax";
	public static final String USER_PARAM_ERROR = "Couldn't make user command";

	public static String parsingError(String s) {
		return String.format("Could not parse command %s", s);
	}

	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	public static final double SCENE_WIDTH = 1200;
	public static final double SCENE_HEIGHT = SCENE_WIDTH * 0.75;

	public static final double PANEL_ELEMENT_WIDTH = SCENE_WIDTH * 0.5 / 2 - 10;
	public static final double PANEL_ELEMENT_HEIGHT = SCENE_HEIGHT / 3 - 60;

	public static final double LEFT_COLUMN_WIDTH = SCENE_WIDTH * (6 / 12.0);
	public static final double RIGHT_COLUMN_WIDTH = SCENE_WIDTH * (2 / 6.0);

	public static final double PLAYGROUND_WIDTH = LEFT_COLUMN_WIDTH;
	public static final double PLAYGROUND_HEIGHT = SCENE_HEIGHT * (7 / 12.0);

	public static final double CENTER_X_COORDINATE = PLAYGROUND_WIDTH / 2;
	public static final double CENTER_Y_COORDINATE = PLAYGROUND_HEIGHT / 2;

	public static final double TEXTAREA_WIDTH = PLAYGROUND_WIDTH * 0.75;
	public static final double TEXTAREA_HEIGHT = SCENE_HEIGHT * (3 / 12.0) - 26;

	public static final double RUN_BUTTON_WIDTH = PLAYGROUND_WIDTH - TEXTAREA_WIDTH;
	public static final double RUN_BUTTON_HEIGHT = TEXTAREA_HEIGHT / 2.0;

	public static final double CLEAR_BUTTON_WIDTH = SCENE_HEIGHT * (1 / 6.0);
	public static final double CLEAR_BUTTON_HEIGHT = TEXTAREA_HEIGHT / 2.0;

	// public static final double VARIABLES_WIDTH = SCENE_WIDTH * (3 / 12.0) -
	// 17;
	// public static final double VARIABLES_HEIGHT = SCENE_HEIGHT * (3 / 12.0);
	// public static final double COLORS_WIDTH = SCENE_WIDTH * (3 / 12.0) - 17;
	// public static final double COLORS_HEIGHT = SCENE_HEIGHT * (3 / 12.0);
	// public static final double COMMANDS_WIDTH = SCENE_WIDTH * (3 / 12.0) -
	// 17;
	// public static final double COMMANDS_HEIGHT = SCENE_HEIGHT * (3 / 12.0);
	// public static final double HISTORY_WIDTH = SCENE_WIDTH * (3 / 12.0) - 17;
	// public static final double HISTORY_HEIGHT = SCENE_HEIGHT * (3 / 12.0);
	// public static final double OUTPUT_WIDTH = SCENE_WIDTH * (3 / 12.0) - 17;
	// public static final double OUTPUT_HEIGHT = SCENE_HEIGHT * (3 / 12.0);
	// public static final double TURTLE_INFO_WIDTH = SCENE_WIDTH * (3 / 12.0) -
	// 17;
	// public static final double TURTLE_INFO_HEIGHT = SCENE_HEIGHT * (3 /
	// 12.0);

	// public static final double RIGHT_COLUMN_ELEMENT_HEIGHT =
	// SCENE_HEIGHT*(3/12.0);

	public static final int TURTLE_ELEMENT_WIDTH = 50;
	public static final int TURTLE_ELEMENT_HEIGHT = TURTLE_ELEMENT_WIDTH;
	public static final Double DEFAULT_TURTLE_PEN_WIDTH = 4.0;
	public static final Double PEN_WIDTH_MIN = 0.0;
	public static final Double PEN_WIDTH_MAX = 10.0;

	public static final int MENU_BAR_HEIGHT = 50;
	public static final int BACK_BUTTON_HEIGHT = MENU_BAR_HEIGHT / 3;
	public static final int MENU_TURTLE_IMAGE_SIZE = 50;
	public static final int INFO_MENU_HEIGHT = 200;
	public static final int INFO_MENU_WIDTH = 130;
	public static final double TURTLE_INFO_BUTTON_SIZE = 23.0;

	public static final int ANIMATION_SLIDER_MIN = 10;
	public static final int ANIMATION_SLIDER_MAX = 50;
	public static final int DEFAULT_ANIMATION_SPEED = 25;

	public static final CornerRadii CORNER_RADIUS = new CornerRadii(10.0);
	public static final double ELEMENT_INSET_HORIZONTAL = 5;
	public static final double ELEMENT_INSET_VERTICAL = 0;

	public static final double DEFAULT_VARIABLE_VALUE = 0;
	public static final int INITIAL_TAB_INDEX = 0;

	public static String getSpecification(String name) {
		return SPECIFICATIONS.getString(name);
	}

	public static String getCommand(String language, String command) throws Exception {
		ResourceBundle COMMANDS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGES + language);
		for (String e : COMMANDS.keySet()) {
			for (String f : COMMANDS.getString(e).split("\\|")) {
				f = f.replace("\\", "");
				if (f.equals(command)) {
					return e;
				}
			}
		}
		throw new ParseException();
	}

	public static boolean isCommand(String command) {
		ResourceBundle COMMANDS = ResourceBundle
				.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGES + LANGUAGES.get(0));
		return COMMANDS.containsKey(command);
	}

	public static int getNumberParams(String superclass) {
		return Integer.parseInt(NUMBER_OF_PARAMS.getString(superclass));
	}

	public static String getAction(String action) {
		return ACTIONS.getString(action);
	}

	public static List<String> getTurtleImages() {
		return DEFAULT_TURTLE_IMAGES;
	}

	public static List<String> getLanguages() {
		return LANGUAGES;
	}

	public static String getDefaultTurtleImageFileName() {
		return "images/" + DEFAULT_TURTLE_IMAGES.get(0) + ".jpg";
	}

	public static String getActionSuperClass(String key) {
		ResourceBundle COMMANDS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_ACTIONSUPERCLASS);
		return COMMANDS.getString(key);
	}

	public static String getShapeMap(int key) {
		ResourceBundle COMMANDS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_SHAPEMAP);
		return COMMANDS.getString(Integer.toString(key));
	}
}
