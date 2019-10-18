package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class SineCommand implements CommandInterface {
    private double angle;

    public SineCommand(double angle) {
        this.angle = angle;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return Math.sin(Math.toRadians(angle));
    }
}
