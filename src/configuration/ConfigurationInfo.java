/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package configuration;

import javafx.scene.paint.Color;
import model.Palette;
import model.UserCommands;
import model.Variables;

public class ConfigurationInfo {

	private Color myPenColor;
	private Color myBackgroundColor;
	private Double myPenWidth;
	private String myLanguage;
	private Variables myVariables;
	private UserCommands myCommands;
	private Palette myPalette;

	/**
	 * This class solely exists to hold the necessary configuration information
	 * to be read, stored, and passed between the XML files and the Model
	 */
	public ConfigurationInfo() {
	}

	public Color getMyPenColor() {
		return myPenColor;
	}

	public void setMyPenColor(Color penColor) {
		this.myPenColor = penColor;
	}

	public Color getMyBackgroundColor() {
		return myBackgroundColor;
	}

	public void setMyBackgroundColor(Color backgroundColor) {
		this.myBackgroundColor = backgroundColor;
	}

	public Double getMyPenWidth() {
		return myPenWidth;
	}

	public void setMyPenWidth(Double penWidth) {
		this.myPenWidth = penWidth;
	}

	public String getMyLanguage() {
		return myLanguage;
	}

	public void setMyLanguage(String language) {
		this.myLanguage = language;
	}

	public Variables getMyVariables() {
		return myVariables;
	}

	public void setMyVariables(Variables myVariables) {
		this.myVariables = myVariables;
	}

	public UserCommands getMyCommands() {
		return myCommands;
	}

	public void setMyCommands(UserCommands commands) {
		this.myCommands = commands;
	}

	public void setMyPalette(Palette palette) {
		myPalette = palette;
	}
	
	public Palette getMyPalette() {
		return myPalette;
	}
}
