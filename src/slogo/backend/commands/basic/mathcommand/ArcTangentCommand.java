package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class ArcTangentCommand implements CommandInterface {

    private static final double FULL_CYCLE = 360;

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        double degree =  Math.toDegrees(Math.atan(parameters.get(0)));
        return degree >= 0? degree : FULL_CYCLE + degree;
    }
}
