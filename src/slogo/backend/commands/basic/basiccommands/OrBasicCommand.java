package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class OrBasicCommand implements BasicCommandInterface {

    public OrBasicCommand() {}

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return parameters.get(0) != 0 || parameters.get(1) != 0 ? 1d : 0d;
    }
}
