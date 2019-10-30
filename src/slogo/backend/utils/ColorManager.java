package slogo.backend.utils;

import javafx.scene.paint.Color;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ColorManager {

    private static final Color INITIAL_PEN_COLOR = Color.BLACK;
    private static final Color INITIAL_BACKGROUND_COLOR = Color.WHITE;
    private static final String RESOURCE_PATH = "resources.frontend.ColorRGBResource";

    private Map<Integer, Color> myColorMap = new TreeMap<>();

    public ColorManager() {
        initialize();
    }

    public Color getColor(int index, boolean background) {
        if(myColorMap.containsKey(index)) {
            return myColorMap.get(index);
        }
        return background? INITIAL_BACKGROUND_COLOR : INITIAL_PEN_COLOR;
    }

    public void addColor(int index, int red, int blue, int green) {
        Color color = new Color(red/256, blue/256, green/256, 1);
        myColorMap.put(index, color);
    }

    private void addColor(String s) {
        String[] arr = s.split(",");
        addColor(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), 100);
    }

    private void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        for(String key : resourceBundle.keySet()) {
            addColor(resourceBundle.getString(key));
        }
    }
}
