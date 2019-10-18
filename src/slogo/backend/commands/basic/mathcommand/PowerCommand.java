package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class PowerCommand implements CommandInterface {

    private double base;
    private double exponent;

    public PowerCommand(double base, double expononent) {
        this.base = base;
        this.exponent = expononent;
    }

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return Math.pow(base, exponent);
    }
}
