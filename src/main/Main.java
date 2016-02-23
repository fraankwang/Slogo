/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage s) throws Exception {
		MainView view = new MainView(s);
		MainController controller = new MainController(view);
		view.setController(controller);
		view.display();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
