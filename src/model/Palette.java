package model;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class Palette {

	Map<Integer, Color> paletteMap;

	public Palette() {
		paletteMap = new HashMap<Integer, Color>();
	}

	public void addColor(int index, Color color) {
		paletteMap.put(index, color);
	}

	public Color getColor(int index) {
		return paletteMap.get(index);
	}
}
