package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class GreaterThanBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return parameters.get(0) > parameters.get(1) ? 1d : 0d;
    }
}
