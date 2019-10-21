package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;

import java.util.List;

public class ArcTangentBasicCommand implements BasicCommandInterface {

    private static final double FULL_CYCLE = 360;

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        double degree =  Math.toDegrees(Math.atan(parameters.get(0)));
        return degree >= 0? degree : FULL_CYCLE + degree;
    }
}
