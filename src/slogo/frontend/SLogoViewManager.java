package slogo.frontend;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleMovement;

import java.util.ArrayList;
import java.util.List;

public class SLogoViewManager {
    private List<TurtleView> turtleViewList = new ArrayList<>();
    private List<List<TurtleMovement>> turtleMovements = new ArrayList<>();
    private ImageManager imageManager;
    private Pane turtlePane;
    private Image image;


    public SLogoViewManager(Pane pane) {
        turtlePane = pane;
        imageManager = new ImageManager();
        setImage(1);
        addTurtleView(1);
    }

    protected void setHistory(TurtleHistory turtleHistory) {
        turtleMovements = turtleHistory.getMyTurtleHistory();
        for(TurtleMovement turtleMovement : turtleMovements.get(0)) {
            getTurtleView(turtleMovement.getTurtleID()).addMovement(turtleMovement);
        }
    }

    protected void update() {
        for(TurtleView turtleView : turtleViewList) {
            if(turtleView.isMoving()) {
                Line line = turtleView.updateAndGetLine();
                if(line != null) {
                    line.setStroke(turtleView.getMyLineColor());
                    turtlePane.getChildren().add(line);
                    turtlePane.getChildren().remove(turtleView);
                    turtlePane.getChildren().add(turtleView);
                }
            }
        }
    }

    protected void setImage(int imageNum) {
        if(imageManager.getImage(imageNum)!=null) {
            image = imageManager.getImage(imageNum);
        }
        //return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("START.png")));
    }

    protected void setLineColor(Paint color) {
        for(TurtleView turtleView : turtleViewList) {
            turtleView.setMyLineColor(color);
        }
    }

    private TurtleView getTurtleView(int myID) {
        for(TurtleView turtleView : turtleViewList) {
            if(turtleView.getMyID() == myID) {
                return turtleView;
            }
        }
        addTurtleView(myID);
        return getTurtleView(myID);
    }

    private void addTurtleView(int myID) {
        if(!hasTurtleView(myID)) {
            TurtleView turtleView = new TurtleView(image, myID, turtlePane.getWidth(), turtlePane.getHeight());
            turtleViewList.add(turtleView);
            turtlePane.getChildren().add(turtleView);
            turtleView.setSpeed(0.2);
            turtleView.setX(turtlePane.getWidth()/2 - turtleView.getFitWidth()/2);
            turtleView.setY(turtlePane.getHeight()/2 - turtleView.getFitHeight()/2);
        }
    }

    private boolean hasTurtleView(int myID) {
        for(TurtleView turtleView : turtleViewList) {
            if(turtleView.getMyID() == myID) {
                return true;
            }
        }
        return false;
    }
}
