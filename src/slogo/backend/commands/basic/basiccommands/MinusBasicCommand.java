package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class MinusBasicCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return parameters.get(0) * (-1);
    }
}
