package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;

import java.util.List;

public class SineCommand implements CommandInterface {
    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return Math.sin(Math.toRadians(parameters.get(0)));
    }
}
