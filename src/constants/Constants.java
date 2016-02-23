/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package constants;

import java.util.ResourceBundle;

public class Constants {
	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private static final String DEFAULT_RESOURCE_FILE = "Specifications";
    private static final String DEFAULT_LANGUAGE = "languages/English";
    private static final ResourceBundle SPECIFICATIONS =
            ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_RESOURCE_FILE);
    private static final ResourceBundle COMMANDS =
    		ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANGUAGE);
    
    public static String getSpecification (String name) {
    	return SPECIFICATIONS.getString(name);
    }
    
    public static String getCommand (String command) {
    	return COMMANDS.getString(command);
    }
    
}
