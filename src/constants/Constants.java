/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package constants;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Constants {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_RESOURCE_FILE = "Specifications";
	private static final String DEFAULT_LANGUAGE = "languages/English";
	private static final String DEFAULT_ACTIONS = "possibleactions";

	private static final ResourceBundle SPECIFICATIONS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);
	private static final ResourceBundle COMMANDS = ResourceBundle
			.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE);
	private static final ResourceBundle ACTIONS = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_ACTIONS);

	private static final List<String> TURTLE_IMAGES = Arrays.asList("basic", "duvall");
	private static final List<String> LANGUAGES = Arrays.asList("English", "Spanish", "Chinese", "French", "German",
			"Italian", "Portuguese", "Russian");

	public static String getSpecification(String name) {
		return SPECIFICATIONS.getString(name);
	}

	public static String getCommand(String command) {
		return COMMANDS.getString(command);
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