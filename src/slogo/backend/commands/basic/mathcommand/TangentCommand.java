package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class TangentCommand implements CommandInterface {
    private double angle;

    public TangentCommand(double angle) {
        this.angle = angle;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return Math.tan(Math.toRadians(angle));
    }
}
