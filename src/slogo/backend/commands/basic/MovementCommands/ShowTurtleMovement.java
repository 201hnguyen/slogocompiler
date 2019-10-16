package slogo.backend.commands.basic.MovementCommands;

import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public class ShowTurtleMovement implements MovementCommandsInterface {
    @Override
    public double getValue(TurtleSetting turtleSetting, List<Double> parameters) { return 1; }

    @Override
    public Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters) {
        return new Movement(turtleSetting.getCurCoordinate(), turtleSetting.getCurCoordinate(),
                turtleSetting.getCurOrientation(), true, turtleSetting.isPenDown(), false);
    }
}
