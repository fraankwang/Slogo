/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.MainView;

public class Main extends Application {

	@Override
	public void start(Stage s) throws Exception {
		MainView view = new MainView(s);
		MainController controller = new MainController(view);
		view.linkController(controller);
		view.init();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
