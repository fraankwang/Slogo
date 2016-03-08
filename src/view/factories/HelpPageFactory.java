/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package view.factories;

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
		Button back = new Button(Constants.getSpecification("BackButton"));
		back.setPrefSize(Constants.SCENE_WIDTH, Constants.MENU_BAR_HEIGHT/3);
		back.setOnAction(e -> myController.setPrimaryPane());
		return back;

	}

	/**
	 * @return WebView that loads help page
	 */
	public WebView createBasicHelpPage() {
		return createWebView(Constants.getSpecification("BasicHelpURL"));

	}

	/**
	 * @return WebView that loads advanced help page
	 */
	public WebView createAdvancedHelpPage() {
		return createWebView(Constants.getSpecification("AdvancedHelpURL"));

	}

	/**
	 * Creates new formatted WebView (annoying JavaFX workaround with global
	 * WebView variables)
	 * 
	 * @param url
	 * @return
	 */
	private WebView createWebView(String url) {
		WebView wv = new WebView();
		wv.setPrefHeight(Constants.SCENE_HEIGHT - Constants.MENU_BAR_HEIGHT);
		wv.getEngine().load(url);
		return wv;
	}

}
