package slogo.frontend;

import javafx.scene.paint.Color;
import slogo.backend.utils.ColorManager;

import java.util.HashMap;
import java.util.Map;

public class ColorAndPenStatus {
    private Map<TurtleView, Color> myLineColors = new HashMap<>();
    private ColorManager colorManager;
    private Color currentLineColor = Color.BLACK;
    private Color currentBackgroundColor = Color.WHITE;
    private Map<TurtleView, Double> myPenSizes = new HashMap<>();
    private double currentPenSize = 1;

    public ColorAndPenStatus(ColorManager colorManager) {
        this.colorManager = colorManager;
    }

    public void setLineColor(TurtleView turtle, Color color) {
        myLineColors.put(turtle, color);
        currentLineColor = color;
    }

    public void setLineColor(TurtleView turtle, int index) {
        myLineColors.put(turtle, colorManager.getColor(index, false));
    }

    public Color getLineColor(TurtleView turtleView) {
        return myLineColors.get(turtleView);
    }

    public void addLineColor(TurtleView turtleView) {
        myLineColors.put(turtleView, currentLineColor);
    }

    public void setColorManager(ColorManager colorManager) {this.colorManager = colorManager;}

    public void setBackgroundColor(int index) {
        currentBackgroundColor = colorManager.getColor(index, true);
    }

    public void setBackgroundColor(Color color) {
        currentBackgroundColor = color;
    }

    public Color getBackgroundColor() {
        return currentBackgroundColor;
    }

    public void setPenSize(TurtleView turtleView, double penSize, boolean changeDefaultSize) {
        myPenSizes.put(turtleView, penSize);
        if(changeDefaultSize) {
            currentPenSize = penSize;
        }
    }

    public void addPenSize(TurtleView turtleView) {myPenSizes.put(turtleView, currentPenSize);}

    public double getPenSize(TurtleView turtleView) {return myPenSizes.get(turtleView);}
}
