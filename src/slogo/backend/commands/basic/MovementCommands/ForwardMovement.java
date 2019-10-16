package slogo.backend.commands.basic.MovementCommands;

import javafx.geometry.Point2D;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class ForwardMovement implements MovementCommandsInterface {
    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        double angle = turtleSetting.getCurOrientation();
        Point2D endingPos = turtleSetting.getCurCoordinate().add(
                new Point2D(parameters.get(0) * Math.cos(angle), parameters.get(0) * Math.sin(angle)));
        return new Movement(turtleSetting.getCurCoordinate(), endingPos, angle, turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
    }
}
