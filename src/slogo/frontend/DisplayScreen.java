package slogo.frontend;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import slogo.backend.utils.TurtleHistory;


public class DisplayScreen extends Pane {
    private static final double SCENE_WIDTH = 600;
    private static final double SCENE_HEIGHT = 400;

    private SLogoViewManager myViewManager;

    public DisplayScreen(){
        setPrefHeight(SCENE_HEIGHT);
        setPrefWidth(SCENE_WIDTH);
        setHeight(SCENE_HEIGHT);
        setWidth(SCENE_WIDTH);
        myViewManager = new SLogoViewManager(this);
    }

    public void setBackground(Color color) {
        BackgroundFill fill = new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY);
        setBackground(new Background(fill));
    }

    public void update() {
        myViewManager.update();
    }

    public void setHistory(TurtleHistory turtleHistory) {
        myViewManager.setHistory(turtleHistory);
    }

    public void setLineColor(Color color) {
        myViewManager.setLineColor(color);
    }

    public void setImage(int imageNum) {
        myViewManager.setImage(imageNum);
    }
}
