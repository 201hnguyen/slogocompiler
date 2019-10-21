package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

import static java.lang.Math.log;

public class NaturalLogBasicCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return log(parameters.get(0));
    }
}
