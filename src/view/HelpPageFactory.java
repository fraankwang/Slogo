/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view;

import java.net.URL;
import constants.Constants;
import controller.MainController;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class HelpPageFactory {

	private MainController myController;

	public HelpPageFactory(MainController controller) {
		myController = controller;
	}

	public Button createBackButton() {
		Button back = new Button("Back");
		back.setPrefSize(Constants.SCENE_WIDTH, Constants.MENU_BAR_HEIGHT);
		back.setOnAction(e -> myController.setPrimaryPane());

		return back;
	}

	public WebView createHTMLPage() {
		WebView htmlPage = new WebView();
		WebEngine webEngine = htmlPage.getEngine();
		URL helpURL = getClass().getClassLoader().getResource("help.html");
		webEngine.load(helpURL.toExternalForm());
		return htmlPage;
	}

}
