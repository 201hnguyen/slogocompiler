package slogo.backend.commands.basic.booleancommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class NotCommand implements CommandInterface {
    private double value;

    public NotCommand(double value) {
        this.value = value;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return value == 0 ? 0d : 1d;
    }
}
