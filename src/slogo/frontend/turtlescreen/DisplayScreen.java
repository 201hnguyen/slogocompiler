package slogo.frontend.turtlescreen;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    public void setPenSize(int penSize) {
        myViewManager.setPenSize(penSize);
    }

    public void setAnimation(double ratio) {
        myViewManager.setSpeed(ratio);
    }

    public void step() {
        myViewManager.step();
    }

    public int getCurrentIndex() {return myViewManager.getCurrentInstructionIndex();}
}
