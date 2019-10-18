package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class CosineCommand implements CommandInterface {
    private double angle;

    public CosineCommand(double angle) {
        this.angle = angle;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return Math.cos(Math.toRadians(angle));
    }
}
