package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class RemainderCommand implements CommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        double parameter1 = parameters.get(0);
        double parameter2 = parameters.get(1);
        return parameter1 - (int) (parameter1 / parameter2) * parameter2 ;
    }
}
