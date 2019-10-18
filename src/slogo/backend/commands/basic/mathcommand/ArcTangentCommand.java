package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class ArcTangentCommand implements CommandInterface {

    private static final double FULL_CYCLE = 360;

    private double value;

    public ArcTangentCommand(double value) {
        this.value = value;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        double degree =  Math.toDegrees(Math.atan(value));
        return degree >= 0? degree : FULL_CYCLE + degree;
    }
}
