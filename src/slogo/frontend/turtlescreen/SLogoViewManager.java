package slogo.frontend.turtlescreen;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.backend.utils.ColorManager;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleMovement;
import slogo.util.DrawStatus;
import slogo.util.PenStatus;

import java.util.ArrayList;
import java.util.List;

public class SLogoViewManager {

    private static final double INITIAL_SPEED = 2;
    private static final PenStatus INITIAL_PEN_STATUS = new PenStatus(true, 1, 1);
    private static final DrawStatus INITIAL_DRAW_STATUS = new DrawStatus(true, 1, 1, false);

    private List<TurtleView> turtleViewList = new ArrayList<>();
    private List<List<TurtleMovement>> turtleMovements = new ArrayList<>();
    private ImageManager imageManager;
    private ColorManager colorManager = new ColorManager();
    private ColorAndPenStatus myColorAndPenStatus;
    private DisplayScreen turtlePane;
    private Image image;
    private double speed = INITIAL_SPEED;
    private PenStatus penStatus;
    private DrawStatus drawStatus;
    private int index = 0;
    private List<Line> lines = new ArrayList<>();


    public SLogoViewManager(DisplayScreen displayScreen) {
        penStatus = INITIAL_PEN_STATUS;
        drawStatus = INITIAL_DRAW_STATUS;
        myColorAndPenStatus = new ColorAndPenStatus(colorManager);
        turtlePane = displayScreen;
        imageManager = new ImageManager();
        setImage(2);
        addTurtleView(1);
    }


    protected void setHistory(TurtleHistory turtleHistory) {
        turtleMovements.clear();
        index = 0;
        turtleMovements.addAll(turtleHistory.getMyTurtleHistory());
        System.out.println(turtleMovements.size() + " is the size");
        allocateTurtleMovements();
    }

    protected void update() {
        int movingNum = 0;
        for(TurtleView turtleView : turtleViewList) {
            if (turtleView.isMoving()) {
                movingNum++;
                Line line = turtleView.updateAndGetLine();
                updateDrawing(turtleView);
                if (turtleView.getPenStatus().isPenSizeChanged()) {
                    myColorAndPenStatus.setPenSize(turtleView, turtleView.getPenStatus().getPenSize(), false);
                }
                if (line != null) {
                    line.setStroke(myColorAndPenStatus.getLineColor(turtleView));
                    line.setStrokeWidth(myColorAndPenStatus.getPenSize(turtleView));
                    turtlePane.getChildren().add(line);
                    turtlePane.getChildren().remove(turtleView);
                    turtlePane.getChildren().add(turtleView);
                    lines.add(line);
                }
                if(turtleView.getDrawStatus().screenToBeErased()) {
                    for(Line myLine: lines) {
                        turtlePane.getChildren().remove(myLine);
                    }
                    lines.clear();
                }
            }
        }
        if(movingNum == 0 && index < turtleMovements.size()-1) {
            index++;
            allocateTurtleMovements();
        }
    }

    protected void setImage(int imageNum) {
        if(imageManager.getImage(imageNum)!=null) {
            image = imageManager.getImage(imageNum);
        }
        for(TurtleView turtleView : turtleViewList) {
            turtleView.setImage(image);
        }
        drawStatus.setImageNum(imageNum);
    }

    protected void setLineColor(Color color) {
        myColorAndPenStatus.setDefaultLineColor(color);
    }

    public void setPenSize(double penSize) {
        penStatus.setPenSize(penSize);
        for(TurtleView turtleView : turtleViewList) {
            myColorAndPenStatus.setPenSize(turtleView, penSize, true);
        }
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
        for (TurtleView turtleView : turtleViewList) {
            turtleView.setSpeed(speed);
        }
    }

    public void setAnimation(String animationString) {
        /**
         * TODO: do this
         */
    }

    protected boolean finishedMoving() {
        return index >= turtleMovements.size()-1;
    }

    public int getCurrentInstructionIndex() {
        return index;
    }

    private void updateDrawing(TurtleView turtleView) {
        DrawStatus drawStatus = turtleView.getDrawStatus();
        if(drawStatus.isVisibleChanged()) {
            turtleView.setVisible(drawStatus.isTurtleVisible());
        }
        if(drawStatus.isImageChanged()) {
            turtleView.setImage(imageManager.getImage(drawStatus.getImageNum()));
        }
        if(drawStatus.isBackGroundChanged()) {
            myColorAndPenStatus.setBackgroundColor(drawStatus.getImageNum());
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
            myColorAndPenStatus.addLineColor(turtleView);
            myColorAndPenStatus.addPenSize(turtleView);
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

    private void allocateTurtleMovements() {
        if(index >= turtleMovements.size()-1) {
            return;
        }
        for(TurtleMovement turtleMovement : turtleMovements.get(index)) {
            getTurtleView(turtleMovement.getTurtleID()).addMovement(turtleMovement);
        }
    }
}
