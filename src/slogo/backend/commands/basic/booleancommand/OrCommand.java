package slogo.backend.commands.basic.booleancommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class OrCommand implements CommandInterface {

    public OrCommand() {}

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) != 0 || parameters.get(1) != 0 ? 1d : 0d;
    }
}
