package slogo.backend.commands.basic.MovementCommands;

import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleSetting;

import java.util.List;

public interface MovementCommandsInterface {

    double getValue(TurtleSetting turtleSetting, List<Double> parameters);

    Movement getMovement(TurtleSetting turtleSetting, List<Double> parameters);
}
