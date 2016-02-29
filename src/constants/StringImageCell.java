/**
 * Authors: Frank Wang, Srikar Pyda, Huijia Yu, Samuel Toffler
 */

package constants;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StringImageCell extends ListCell<String> {

	private Label label;
	private final int TOOLBAR_IMAGE_SIZE = Constants.TOOLBAR_IMAGE_SIZE;
	
	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		if (item == null || empty) {
			setItem(null);
			setGraphic(null);
		} else {
			setText(item);
			ImageView image = getImageView(item);
			label = new Label("", image);
			setGraphic(label);
		}
	}

	private ImageView getImageView(String item) {
		String path = item + ".jpg";
		ImageView iv = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
		iv.setFitHeight(TOOLBAR_IMAGE_SIZE);
		iv.setFitWidth(TOOLBAR_IMAGE_SIZE);
		return iv;

	}

}