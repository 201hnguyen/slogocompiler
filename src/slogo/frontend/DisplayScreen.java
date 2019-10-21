package slogo.frontend;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;


public class DisplayScreen extends Pane {
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
