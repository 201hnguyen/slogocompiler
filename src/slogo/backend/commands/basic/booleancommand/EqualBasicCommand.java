package slogo.backend.commands.basic.booleancommand;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class EqualBasicCommand implements BasicCommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) == parameters.get(1) ? 1d : 0d;
    }
}
