package slogo.frontend;

import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class DisplayScreen extends StackPane {
    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 400;

    public DisplayScreen(){
        setPrefHeight(SCENE_HEIGHT);
        setPrefWidth(SCENE_WIDTH);
    }

    public void setBackground(Color color) {
        BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        setBackground(new Background(fill));
    }

}
