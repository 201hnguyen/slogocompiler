package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

import static java.lang.Math.log;

public class NaturalLogCommand implements CommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return log(parameters.get(0));
    }
}
