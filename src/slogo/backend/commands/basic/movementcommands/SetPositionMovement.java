package slogo.backend.commands.basic.movementcommands;

import javafx.geometry.Point2D;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class SetPositionMovement implements MovementCommandsInterface {
    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
        return turtleSetting.getCurCoordinate().distance(parameters.get(0), parameters.get(1));
    }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        return new Movement(turtleSetting.getCurCoordinate(), new Point2D(parameters.get(0), parameters.get(1)),
                turtleSetting.getCurOrientation(), turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
    }
}
