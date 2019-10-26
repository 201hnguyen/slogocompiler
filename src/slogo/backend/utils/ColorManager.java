package slogo.backend.utils;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.util.Map;
import java.util.TreeMap;

public class ColorManager {

    private static final Paint INITIAL_PEN_COLOR = Color.BLACK;
    private static final Paint INITIAL_BACKGROUND_COLOR = Color.WHITE;

    private Map<Integer, Paint> myColorMap = new TreeMap<>();

    public void addColor(int index, double red, double blue, double green) {
        Paint paint = new Color(red, blue, green, 1.0);
        myColorMap.put(index, paint);
    }

    public Paint getColor(int index, boolean background) {
        if(myColorMap.containsKey(index)) {
            return myColorMap.get(index);
        }
        return background? INITIAL_BACKGROUND_COLOR : INITIAL_PEN_COLOR;
    }
}
