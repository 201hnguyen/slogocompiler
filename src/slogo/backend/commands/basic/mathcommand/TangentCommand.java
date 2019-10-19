package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class TangentCommand implements CommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return Math.tan(Math.toRadians(parameters.get(0)));
    }
}
