package slogo.frontend;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleMovement;

import java.util.ArrayList;
import java.util.List;


public class TurtleView extends ImageView {

    private static final double CONSTANT_SCREEN_WIDTH = 400;
    private static final double ABSOLUTE_SIZEX = 30;
    private static final double INITIAL_ORIENTATION = 90;

    private List<TurtleMovement> myMovements = new ArrayList<>();
    private int myID;
    private int index;
    private double ABSOLUTE_SIZEY;
    private double screenWidth;
    private double screenHeight;
    private double speed;

    public TurtleView(Image image, int turtleID, double screenWidth, double screenHeight) {
        super(image);
        myID = turtleID;
        ABSOLUTE_SIZEY = ABSOLUTE_SIZEX * getBoundsInLocal().getHeight() / getBoundsInLocal().getWidth();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        setFitWidth(CONSTANT_SCREEN_WIDTH * ABSOLUTE_SIZEX / screenWidth);
        setFitHeight(getFitWidth() * ABSOLUTE_SIZEY / ABSOLUTE_SIZEX);
    }

    public Line update() {
        Point2D initialPos = new Point2D(getCentralX(), getCentralY());
        Movement movement = myMovements.get(index).getMovement();
        double angle = movement.getOrientation();
        Point2D endPos = new Point2D(movement.getEndPosition().getX() + getScreenCenter().getX(),
                getScreenCenter().getY() - movement.getEndPosition().getY());

        if(initialPos.distance(endPos) < speed) {
            moveView(endPos, angle);
            index++;
            return new Line(initialPos.getX(), initialPos.getY(), endPos.getX(), endPos.getY());
        } else {
            double targetXPos = getCentralX() + speed * Math.cos(Math.toRadians(angle));
            double targetYPos = getCentralY() - speed * Math.sin(Math.toRadians(angle));
            moveView(new Point2D(targetXPos, targetYPos), angle);
            return new Line(initialPos.getX(), initialPos.getY(), targetXPos, targetYPos);
        }
    }

    public void addMovement(TurtleMovement turtleMovement) {
        myMovements.add(turtleMovement);
    }

    public boolean isMoving() {
        return index >= myMovements.size();
    }

    private void moveView(Point2D targetPos, double orientation) {
        setX(targetPos.getX() - getFitWidth()/2);
        setY(targetPos.getY() - getFitHeight()/2);
        setRotate(orientation - INITIAL_ORIENTATION);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    private boolean reachedEnd() {
        return false;
    }

    private double getCentralX() {
        return getX() + getBoundsInLocal().getWidth() / 2;
    }

    private double getCentralY() {
        return getY() + getBoundsInLocal().getHeight() / 2;
    }

    private Point2D getScreenCenter() {
        return new Point2D(screenWidth/2, screenHeight/2);
    }

    //private void
}
