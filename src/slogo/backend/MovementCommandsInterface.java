package slogo.backend;

import javafx.geometry.Point2D;

import java.util.List;

public interface MovementCommandsInterface {

    double getValue(TurtleSetting turtleSetting, List<Double> parameters);

    Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters);
}
