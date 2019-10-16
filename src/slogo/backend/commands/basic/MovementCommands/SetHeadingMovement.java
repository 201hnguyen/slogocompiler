package slogo.backend.commands.basic.MovementCommands;

import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class SetHeadingMovement implements MovementCommandsInterface {
    private static final double HALF_CYCLE = 180d;
    private static final double FULL_CYCLE = 360d;

    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) {
        double degreeTurned = Math.abs(turtleSetting.getCurOrientation() - parameters.get(0));
        return degreeTurned <= HALF_CYCLE ? degreeTurned : FULL_CYCLE - degreeTurned;
    }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(), parameters.get(0),
                turtleSetting.isVisible(), turtleSetting.isPenDown(), false);
    }
}
