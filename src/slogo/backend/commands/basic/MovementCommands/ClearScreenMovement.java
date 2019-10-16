package slogo.backend.commands.basic.MovementCommands;

import javafx.geometry.Point2D;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class ClearScreenMovement implements MovementCommandsInterface {

    private static final double INITIAL_ORIENTATION = 90;

    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
        return turtleSetting.getCurCoordinate().distance(0, 0);
    }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        return new Movement(turtleSetting.getCurCoordinate(), new Point2D(0, 0), INITIAL_ORIENTATION,
                turtleSetting.isVisible(), turtleSetting.isPenDown(), true);
    }
}
