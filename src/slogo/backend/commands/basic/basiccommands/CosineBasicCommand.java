package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class CosineBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return Math.cos(Math.toRadians(parameters.get(0)));
    }
}
