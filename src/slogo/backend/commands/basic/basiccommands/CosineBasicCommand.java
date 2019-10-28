package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;

public class CosineBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(TurtleHistory turtleHistory, List<Double> parameters, int turtleID) {
        return Math.cos(Math.toRadians(parameters.get(0)));
    }
}
