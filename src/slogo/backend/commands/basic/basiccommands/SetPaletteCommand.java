package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class SetPaletteCommand implements BasicCommandInterface {
    private static final double ACCURACY = 0.001;

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return (int) (ACCURACY + parameters.get(0));
    }
}
