package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import static java.lang.Math.log;

public class NaturalLogCommand implements CommandInterface {

    private static final double FULL_CYCLE = 360;

    private double value;

    public NaturalLogCommand(double value) {
        this.value = value;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return log(value);
    }
}
