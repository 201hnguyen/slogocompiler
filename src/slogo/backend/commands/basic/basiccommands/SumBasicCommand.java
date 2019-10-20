package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class SumBasicCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) + parameters.get(1);
    }
}
