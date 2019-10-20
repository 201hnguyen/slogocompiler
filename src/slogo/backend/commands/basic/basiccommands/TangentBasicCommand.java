package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class TangentBasicCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return Math.tan(Math.toRadians(parameters.get(0)));
    }
}
