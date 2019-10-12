package slogo.backend;

import javafx.geometry.Point2D;

public class Movement {

    private Point2D startPosition;
    private Point2D endPosition;
    private double orientation;
    private boolean visible;
    private boolean penDown;

    public Movement(Point2D startPosition, Point2D endPosition, double orientation, boolean isVisible, boolean isPenDown) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.orientation = orientation;
        this.visible = isVisible;
        this.penDown = isPenDown;
    }

    public Point2D getStartPosition() {
        return startPosition;
    }
    public Point2D getEndPosition() {
        return endPosition;
    }
    public double getOrientation() {
        return orientation;
    }
    public boolean isTurtleVisible() {
        return visible;
    }

    public boolean isPenDown() {
        return penDown;
    }

}
