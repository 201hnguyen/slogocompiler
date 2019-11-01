package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;

public class EqualBasicCommand implements BasicCommandInterface {

    private static final double ACCURACY = 0.0001;

    @Override
    public double getReturnValue(TurtleHistory turtleHistory, List<Double> parameters, int turtleID) {
        return Math.abs(parameters.get(0)-parameters.get(1)) < ACCURACY ? 1d : 0d;
    }
}
