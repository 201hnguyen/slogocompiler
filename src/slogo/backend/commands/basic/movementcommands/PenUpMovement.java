package slogo.backend.commands.basic.movementcommands;

import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class PenUpMovement implements MovementCommandsInterface {
    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 0; }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                turtleSetting.getCurOrientation(), turtleSetting.isVisible(), false, false);
    }
}
