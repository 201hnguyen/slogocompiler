package slogo.frontend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import slogo.backend.utils.DrawStatus;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleMovement;

import java.util.ArrayList;
import java.util.List;


public class TurtleView extends ImageView {

    private static final double CONSTANT_SCREEN_WIDTH = 600;
    private static final double ABSOLUTE_SIZE_X = 30;
    private static final double INITIAL_ORIENTATION = 90;

    private List<TurtleMovement> myMovements = new ArrayList<>();
    private int myID;
    private int index = 0;
    private double ABSOLUTE_SIZE_Y;
    private double screenWidth;
    private double screenHeight;
    private double speed;
    private boolean isPenDown;
    private boolean isVisible;

    public TurtleView(Image image, int turtleID, double screenWidth, double screenHeight) {
        super(image);
        myID = turtleID;
        ABSOLUTE_SIZE_Y = ABSOLUTE_SIZE_X * getBoundsInLocal().getHeight() / getBoundsInLocal().getWidth();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setFitWidth(CONSTANT_SCREEN_WIDTH * ABSOLUTE_SIZE_X / screenWidth);
        setFitHeight(getFitWidth() * ABSOLUTE_SIZE_Y / ABSOLUTE_SIZE_X);
    }

    public Line updateAndGetLine() {
        Point2D initialPos = new Point2D(getCentralX(), getCentralY());
        Movement movement = myMovements.get(index).getMovement();
        updateDrawStatus(myMovements.get(index).getDrawStatus());
        double angle = movement.getOrientation();
        Point2D endPos = new Point2D(movement.getEndPosition().getX() + getScreenCenter().getX(),
                getScreenCenter().getY() - movement.getEndPosition().getY());
        if(initialPos.distance(endPos) < speed) {
            moveView(endPos, angle);
            index++;
            return isPenDown? new Line(initialPos.getX(), initialPos.getY(), endPos.getX(), endPos.getY()) : null;
        } else {
            double targetXPos = getCentralX() + speed * Math.cos(Math.toRadians(angle));
            double targetYPos = getCentralY() - speed * Math.sin(Math.toRadians(angle));
            moveView(new Point2D(targetXPos, targetYPos), angle);
            return isPenDown? new Line(initialPos.getX(), initialPos.getY(), targetXPos, targetYPos) : null;
        }
    }

    public void addMovement(TurtleMovement turtleMovement) {
        myMovements.add(turtleMovement);
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

    private double getCentralX() {
        return getX() + getBoundsInLocal().getWidth() / 2;
    }

    private double getCentralY() {
        return getY() + getBoundsInLocal().getHeight() / 2;
    }

    private Point2D getScreenCenter() {
        System.out.println(new Point2D(screenWidth/2, screenHeight/2));
        return new Point2D(screenWidth/2, screenHeight/2);
    }

    private void updateDrawStatus(DrawStatus drawStatus) {
        isPenDown = drawStatus.isPenDown();
        isVisible = drawStatus.isTurtleVisible();
        setVisible(isVisible);
    }
}
