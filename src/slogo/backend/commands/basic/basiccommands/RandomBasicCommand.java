package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class RandomBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return ((int) Math.random() * parameters.get(0));
    }
}
