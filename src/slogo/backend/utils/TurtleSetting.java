package slogo.backend.utils;

import javafx.geometry.Point2D;

public class TurtleSetting {
    private Point2D curCoordinate;
    private double curOrientation;
    private boolean isVisible;
    private boolean isPenDown;

    public TurtleSetting(Point2D curCoordinate, double curOrientation, boolean isVisible, boolean isPenDown) {
        this.curCoordinate = curCoordinate;
        this.curOrientation = curOrientation;
        this.isVisible = isVisible;
        this.isPenDown = isPenDown;
    }

    public Point2D getCurCoordinate() {
        return curCoordinate;
    }

    public double getCurOrientation() {
        return curOrientation;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isPenDown() {
        return isPenDown;
    }

    public TurtleSetting copyOfThis() {
        return new TurtleSetting(curCoordinate, curOrientation, isVisible, isPenDown);
    }

    public void update(Movement movement) {
        this.curCoordinate = movement.getEndPosition();
        this.curOrientation = movement.getOrientation();
        this.isVisible = movement.isTurtleVisible();
        this.isPenDown = movement.isPenDown();
    }
}
