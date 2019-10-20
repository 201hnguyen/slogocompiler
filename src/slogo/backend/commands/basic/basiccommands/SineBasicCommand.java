package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class SineBasicCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return Math.sin(Math.toRadians(parameters.get(0)));
    }
}
