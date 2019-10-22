package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class QuotientBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return (int) (parameters.get(0) / parameters.get(1));
    }
}
