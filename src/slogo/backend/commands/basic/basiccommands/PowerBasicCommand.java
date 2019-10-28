package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;

public class PowerBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(TurtleHistory turtleHistory, List<Double> parameters, int turtleID) {
        return Math.pow(parameters.get(0), parameters.get(1));
    }
}
