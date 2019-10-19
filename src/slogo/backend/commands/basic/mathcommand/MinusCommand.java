package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class MinusCommand implements CommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) * (-1);
    }
}
