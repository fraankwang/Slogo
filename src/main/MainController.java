/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.MainModel;

public class MainController {
	
	private MainView myView;
	private MainModel myModel;
//	private Timeline myTimeline;
	
	
	public MainController(MainView view) {
		myView = view;
		myModel = new MainModel();
		
	}

	
	public void runCommand (String input) {
		readCommand(input);
		refreshDisplay();
		
	}
	
	private void readCommand (String input) {
		myModel.readCommand(input);
	}
	
	private void refreshDisplay () {
		
	}
	
	private void setTurtleColor (Color color) {
		//updates Model information, needs to refresh display
		
	}
	
	private void setBackgroundColor (Color color) {
		
	}
	
	private void setTurtleImage (Image image) {
		//updates Model information, needs to refresh display
		
	}
	
	private void setPenColor (Color color) {
		//updates Model information, needs to refresh display
		
	}



}
