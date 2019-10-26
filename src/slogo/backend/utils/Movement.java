package slogo.backend.utils;

import javafx.geometry.Point2D;

public class Movement {

    private Point2D startPosition;
    private Point2D endPosition;
    private double orientation;

    public Movement(Point2D startPosition, Point2D endPosition, double orientation) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.orientation = orientation;
    }

    public Movement(Point2D startPosition, double orientation, double length) {
        this.startPosition = startPosition;
        this.orientation = orientation;
        this.endPosition = new Point2D(startPosition.getX() + length * Math.cos(Math.toRadians(orientation)),
                startPosition.getY() + length * Math.sin(Math.toRadians(orientation)));
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
}
