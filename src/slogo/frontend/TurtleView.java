package slogo.frontend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.PenStatus;
import slogo.backend.utils.TurtleMovement;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TurtleView extends ImageView {

    private static final double CONSTANT_SCREEN_WIDTH = 3000;
    private static final double ABSOLUTE_SIZE_X = 30;
    private static final double INITIAL_ORIENTATION = 90;
    private static final double FULL_ANGLE = 360;
    private static final Paint INITIAL_COLOR = Color.BLACK;

    private List<TurtleMovement> myMovements = new ArrayList<>();
    private int myID;
    private int index = 0;
    private double screenWidth;
    private double screenHeight;
    private double speed;
    private int imageNum;
    private int penSize;
    private boolean initialize = false;
    private boolean isPenDown = true;
    private boolean isVisible = true;
    private int direction = 1;
    private Paint myLineColor = INITIAL_COLOR;

    public TurtleView(Image image, int turtleID, double screenWidth, double screenHeight) {
        super(image);
        myID = turtleID;
        double ABSOLUTE_SIZE_Y = ABSOLUTE_SIZE_X * getBoundsInLocal().getHeight() / getBoundsInLocal().getWidth();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setFitWidth(CONSTANT_SCREEN_WIDTH * ABSOLUTE_SIZE_X / screenWidth);
        setFitHeight(getFitWidth() * ABSOLUTE_SIZE_Y / ABSOLUTE_SIZE_X);
    }

    public Line updateAndGetLine() {
        Point2D initialPos = new Point2D(getCentralX(), getCentralY());
        Movement movement = myMovements.get(index).getMovement();
        updateDrawStatus(myMovements.get(index).getDrawStatus());
        updatePenStatus(myMovements.get(index).getPenStatus());
        double angle = getAngle(movement.getStartPosition(), movement.getEndPosition());
        Point2D endPos = new Point2D(movement.getEndPosition().getX() + getScreenCenter().getX(),
                getScreenCenter().getY() - movement.getEndPosition().getY());
        if(initialPos.distance(endPos) < speed) {
            moveView(endPos, movement.getOrientation());
            index++;
            return isPenDown? new Line(initialPos.getX(), initialPos.getY(), endPos.getX(), endPos.getY()) : null;
        } else {
            double targetXPos = getCentralX() +  speed * Math.cos(Math.toRadians(angle));
            double targetYPos = getCentralY() -  speed * Math.sin(Math.toRadians(angle));
            moveView(new Point2D(targetXPos, targetYPos), movement.getOrientation());
            return isPenDown? new Line(initialPos.getX(), initialPos.getY(), targetXPos, targetYPos) : null;
        }
    }

    public void addMovement(TurtleMovement turtleMovement) {

        myMovements.add(turtleMovement);
        System.out.println(turtleMovement.getPenStatus().isPenDown() + ", " + turtleMovement.getPenStatus().isPenDownChanged() + "sdfsdf");
    }

    public boolean isMoving() {
        return index < myMovements.size();
    }

    private void moveView(Point2D targetPos, double orientation) {
        setX(targetPos.getX() - getFitWidth()/2);
        setY(targetPos.getY() - getFitHeight()/2);
        setRotate(INITIAL_ORIENTATION - orientation);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getMyID() {
        return myID;
    }

    public Paint getMyLineColor() {
        return myLineColor;
    }

    public void setMyLineColor(Paint myLineColor) {
        this.myLineColor = myLineColor;
    }

    public void initialize() {moveView(new Point2D(getCentralX(), getCentralY()), INITIAL_ORIENTATION);}

    public int getImageNum() {return imageNum;}

    private double getCentralX() {
        return getX() + getBoundsInLocal().getWidth() / 2;
    }

    private double getCentralY() {
        return getY() + getBoundsInLocal().getHeight() / 2;
    }

    private Point2D getScreenCenter() {
        return new Point2D(screenWidth/2, screenHeight/2);
    }

    private void updateDrawStatus(DrawStatus drawStatus) {
        isVisible = drawStatus.isVisibleChanged() ? drawStatus.isTurtleVisible() : isVisible;
        setVisible(isVisible);
        imageNum = drawStatus.isImageChanged() ? drawStatus.getImageNum() : imageNum;
        initialize = drawStatus.screenToBeErased();
    }

    private void updatePenStatus(PenStatus penStatus) {
        isPenDown = penStatus.isPenDownChanged() ? penStatus.isPenDown() : isPenDown;
    }

//    private void checkDirection(Point2D initialPos, Point2D targetPos, double orientation) {
//        double length = initialPos.distance(targetPos);
//        Point2D plannedArrival = new Point2D(initialPos.getX() + length * Math.cos(orientation),
//                initialPos.getY() + length * Math.sin(orientation));
//
//        direction = plannedArrival.distance(targetPos) > length ? -1 : 1;
//    }

    private double getAngle(Point2D startPos, Point2D targetPos) {
        double angle = new Point2D(1, 0).angle(targetPos.getX() - startPos.getX(), targetPos.getY() - startPos.getY());
        return targetPos.getY() - startPos.getY() < 0 ? FULL_ANGLE - angle : angle;
    }
}
