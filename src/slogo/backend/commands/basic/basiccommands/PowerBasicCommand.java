package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class PowerBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return Math.pow(parameters.get(0), parameters.get(1));
    }
}
