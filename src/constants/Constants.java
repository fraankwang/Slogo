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

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_RESOURCE_FILE = "Specifications";
	private static final String DEFAULT_LANGUAGES = "languages/";

	private static final String DEFAULT_ACTIONS = "possibleactions";
	private static final String DEFAULT_PARAMS = "numberofparams";
	
	public static final String MATH_NOPARAMS = "model.action.MathNoParams.MathNoParams";

	public static final String MATH_ONEPARAM = "model.action.MathOneParam.MathOneParam";
	public static final String MATH_TWOPARAMS = "model.action.MathTwoParams.MathTwoParams";
	public static final String TURTLE_NOCOMMANDS = "model.action.TurtleCommandsNoParams.TurtleCommands";
	public static final String TURTLE_ONEPARAM = "model.action.TurtleCommandsOneParam.TurtleCommandsOneParam";
	public static final String TURTLE_TWOPARAMS= "model.action.TurtleCommandsTwoParams.TurtleCommandsTwoParams";
	public static final String CONTROL_STRUCTURES= "model.action.HigherOrderCommands.ControlStructures";
	public static final String HIGHER_ORDERSTRUCTURE = "model.action.HigherOrderCommands.HigherOrderCommands";
	
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

	public static final Color DEFAULT_PEN_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	public static final int SCENE_WIDTH = 1000;
	public static final int SCENE_HEIGHT = 750;

	public static final int LEFT_COLUMN_WIDTH = 700;
	public static final int PLAYGROUND_HEIGHT = 550;
	public static final int RIGHT_COLUMN_WIDTH = 300;
	public static final int RIGHT_COLUMN_ELEMENT_HEIGHT = 155;

	public static final int TURTLE_ELEMENT_WIDTH = 50;
	public static final int TURTLE_ELEMENT_HEIGHT = TURTLE_ELEMENT_WIDTH;
	public static final int MENU_BAR_HEIGHT = 50;
	public static final int MENU_TURTLE_IMAGE_SIZE = 50;

	public static final int TEXTAREA_HEIGHT = SCENE_HEIGHT - PLAYGROUND_HEIGHT - 30;
	public static final int TEXTAREA_WIDTH = 600;
	public static final int RUN_BUTTON_HEIGHT = TEXTAREA_HEIGHT / 2;
	public static final int RUN_BUTTON_WIDTH = LEFT_COLUMN_WIDTH - TEXTAREA_WIDTH;
	public static final double CLEAR_BUTTON_HEIGHT = RUN_BUTTON_HEIGHT;
	public static final double CLEAR_BUTTON_WIDTH = RUN_BUTTON_WIDTH;

	public static final int ANIMATION_SLIDER_MIN = 10;
	public static final int ANIMATION_SLIDER_MAX = 50;
	public static final int DEFAULT_ANIMATION_SPEED = 25;

	public static final CornerRadii CORNER_RADIUS = new CornerRadii(10.0);
	public static final double ELEMENT_INSET_HORIZONTAL = 5;
	public static final double ELEMENT_INSET_VERTICAL = 0;

	public static String getSpecification(String name) {
		return SPECIFICATIONS.getString(name);
	}

	public static String getCommand(String language, String command) throws Exception {
		ResourceBundle COMMANDS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGES + language);
		for (String e : COMMANDS.keySet()) {
			for (String f : COMMANDS.getString(e).split("\\|")) {
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
		return DEFAULT_TURTLE_IMAGES;
	}

	public static List<String> getLanguages() {
		return LANGUAGES;
	}
	
	public static String getDefaultTurtleImageFileName() {
		return DEFAULT_TURTLE_IMAGES.get(0) + ".jpg"; 
	}
}
