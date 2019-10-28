package slogo.frontend;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import slogo.backend.utils.*;

import java.util.ArrayList;
import java.util.List;

public class SLogoViewManager {

    private static final double INITIAL_SPEED = 2;
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 1, false);

    private List<TurtleView> turtleViewList = new ArrayList<>();
    private List<TurtleMovement> turtleMovements = new ArrayList<>();
    private ImageManager imageManager;
    private ColorManager colorManager;
    private Pane turtlePane;
    private Image image;
    private double speed = INITIAL_SPEED;
    private PenStatus penStatus;
    private DrawStatus drawStatus;


    public SLogoViewManager(Pane pane) {
        penStatus = INITIAL_PEN_STATUS;
        drawStatus = INITIAL_DRAW_STATUS;
        turtlePane = pane;
        imageManager = new ImageManager();
        setImage(2);
        addTurtleView(1);
    }


    protected void setHistory(TurtleHistory turtleHistory) {
        turtleMovements = turtleHistory.getMyTurtleHistory();
        for(TurtleMovement turtleMovement : turtleMovements) {
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
        System.out.println(imageNum + "selected");
        if(imageManager.getImage(imageNum)!=null) {
            image = imageManager.getImage(imageNum);
        }
        for(TurtleView turtleView : turtleViewList) {
            turtleView.setImage(image);
        }
    }

    protected void setLineColor(Paint color) {
        for(TurtleView turtleView : turtleViewList) {
            turtleView.setMyLineColor(color);
        }
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
        for (TurtleView turtleView : turtleViewList) {
            turtleView.setSpeed(speed);
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
            updateTurtleStatus(turtleView);
            turtleViewList.add(turtleView);
            turtlePane.getChildren().add(turtleView);
            turtleView.setSpeed(speed);
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

    private void updateTurtleStatus(TurtleView turtleView) {
        turtleView.setPenStatus(penStatus);
        turtleView.setDrawStatus(drawStatus);
    }

}
