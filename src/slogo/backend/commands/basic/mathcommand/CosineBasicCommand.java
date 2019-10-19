package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class CosineBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return Math.cos(Math.toRadians(parameters.get(0)));
    }
}
