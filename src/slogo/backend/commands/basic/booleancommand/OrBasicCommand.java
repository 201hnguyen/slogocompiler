package slogo.backend.commands.basic.booleancommand;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class OrBasicCommand implements BasicCommandInterface {

    public OrBasicCommand() {}

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return parameters.get(0) != 0 || parameters.get(1) != 0 ? 1d : 0d;
    }
}
