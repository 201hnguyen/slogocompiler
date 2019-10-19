package slogo.backend.commands.basic.booleancommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class NotCommand implements CommandInterface {

    public NotCommand() {}

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) == 0  ? 0d : 1d;
    }
}
