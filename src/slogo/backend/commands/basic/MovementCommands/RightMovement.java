package slogo.backend.commands.basic.MovementCommands;

import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class RightMovement implements MovementCommandsInterface{
    private static final double FULL_CYCLE = 360;

    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return parameters.get(0); }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        double angle = ((turtleSetting.getCurOrientation() - parameters.get(0)) + FULL_CYCLE) % FULL_CYCLE;
        return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), angle,
                turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
    }
}
