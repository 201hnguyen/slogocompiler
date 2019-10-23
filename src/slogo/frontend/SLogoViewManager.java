package slogo.frontend;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SLogoViewManager {
    private List<TurtleView> turtleViewList = new ArrayList<>();
    private List<List<TurtleMovement>> turtleMovements = new ArrayList<>();
    private Pane turtlePane;
    private Image image;

    public SLogoViewManager(Pane pane) {
        turtlePane = pane;
        setImage("START.png");
        addTurtleView(1);
    }

    public void setHistory(TurtleHistory turtleHistory) {
        turtleMovements = turtleHistory.getMyTurtleHistory();
        for(TurtleMovement turtleMovement : turtleMovements.get(0)) {
            getTurtleView(turtleMovement.getTurtleID()).addMovement(turtleMovement);
        }
    }

    public void update() {
        for(TurtleView turtleView : turtleViewList) {
            if(turtleView.isMoving()) {
                Line line = turtleView.updateAndGetLine();
                if(line != null) {
                    turtlePane.getChildren().add(line);
                }
            }
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

    public void setImage(String fileName) {
        image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)));
        //return new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("START.png")));
    }
}
