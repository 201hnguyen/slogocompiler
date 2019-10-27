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
    private PenStatus penStatus;
    private DrawStatus drawStatus;
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
            return penStatus.isPenDown()? new Line(initialPos.getX(), initialPos.getY(), endPos.getX(), endPos.getY()) : null;
        } else {
            double targetXPos = getCentralX() +  speed * Math.cos(Math.toRadians(angle));
            double targetYPos = getCentralY() -  speed * Math.sin(Math.toRadians(angle));
            moveView(new Point2D(targetXPos, targetYPos), movement.getOrientation());
            return penStatus.isPenDown()? new Line(initialPos.getX(), initialPos.getY(), targetXPos, targetYPos) : null;
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

    public void lineColorChangedByUser() {
        penStatus = new PenStatus(penStatus.isPenDown(), penStatus.getPenSize(), -1);
    }

    public void setPenStatus(PenStatus penStatus) {
        this.penStatus = penStatus;
    }

    public void setDrawStatus(DrawStatus drawStatus) {this.drawStatus = drawStatus;}

    public void initialize() {moveView(new Point2D(getCentralX(), getCentralY()), INITIAL_ORIENTATION);}

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
        boolean isVisible = drawStatus.isVisibleChanged() ? drawStatus.isTurtleVisible() : this.drawStatus.isTurtleVisible();
        setVisible(isVisible);
        int imageNum = drawStatus.isImageChanged() ? drawStatus.getImageNum() : this.drawStatus.getImageNum();
        int backGround = drawStatus.isBackGroundChanged() ? drawStatus.getBackGround() : this.drawStatus.getBackGround();
        boolean initialize = drawStatus.screenToBeErased();

        this.drawStatus = new DrawStatus(isVisible, backGround, imageNum, initialize);
    }

    private void updatePenStatus(PenStatus penStatus) {
        boolean isPenDown = penStatus.isPenDownChanged() ? penStatus.isPenDown() : this.penStatus.isPenDown();
        int penSize = penStatus.isPenSizeChanged() ? penStatus.getPenSize() : this.penStatus.getPenSize();
        int penColor = penStatus.isPenColorChanged() ? penStatus.getPenColor() : this.penStatus.getPenColor();

        this.penStatus = new PenStatus(isPenDown, penSize, penColor);
    }

    private double getAngle(Point2D startPos, Point2D targetPos) {
        double angle = new Point2D(1, 0).angle(targetPos.getX() - startPos.getX(), targetPos.getY() - startPos.getY());
        return targetPos.getY() - startPos.getY() < 0 ? FULL_ANGLE - angle : angle;
    }
}
