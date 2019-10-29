package slogo.backend.utils;

import javafx.scene.paint.Color;
import java.util.Map;
import java.util.TreeMap;

public class ColorManager {

    private static final Color INITIAL_PEN_COLOR = Color.BLACK;
    private static final Color INITIAL_BACKGROUND_COLOR = Color.WHITE;

    private Map<Integer, Color> myColorMap = new TreeMap<>();

    public void addColor(int index, double red, double blue, double green) {
        Color color = new Color(red, blue, green, 1.0);
        myColorMap.put(index, color);
    }

    public Color getColor(int index, boolean background) {
        if(myColorMap.containsKey(index)) {
            return myColorMap.get(index);
        }
        return background? INITIAL_BACKGROUND_COLOR : INITIAL_PEN_COLOR;
    }
}
