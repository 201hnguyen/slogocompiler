package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class NotBasicCommand implements BasicCommandInterface {

    public NotBasicCommand() {}

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) == 0  ? 0d : 1d;
    }
}
