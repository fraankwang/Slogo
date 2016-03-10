package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import constants.Constants;
import javafx.scene.paint.Color;

public class Palette {

	Map<Integer, Color> paletteMap;
	Map<Integer, String> shapeMap;

	public Palette() {
		paletteMap = new HashMap<Integer, Color>();
		shapeMap = new HashMap<Integer, String>();
		initShapeMap();
	}

	private void initShapeMap() {
		for (int i=1; i<4;i++){
			addShape(i, Constants.getShapeMap(i));
		}
		
	}

	public void addColor(int index, Color color) {
		paletteMap.put(index, color);
	}

	public Color getColor(int index) {
		return paletteMap.get(index);
	}

	public int getColorIndex(Color color) {
		for (Entry<Integer, Color> entry : paletteMap.entrySet()) {
			if (entry.getValue().equals(color)) {
				return entry.getKey();
			}
		}
		return -1;
	}

	public void addShape(int index, String shape) {
		shapeMap.put(index, shape);
	}

	public String getShape(int index) {
		return shapeMap.get(index);
	}

	public int getShapeIndex(String shape){
		for (Entry<Integer, String> entry : shapeMap.entrySet()) {
	        if (entry.getValue().equals(shape)) {
	            return entry.getKey();
	        }
	    }
	    return -1;
	}
	
	public Map<Integer, Color> getPaletteMap(){
		return paletteMap;
	}
}
