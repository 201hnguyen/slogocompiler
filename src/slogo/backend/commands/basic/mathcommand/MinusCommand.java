package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class MinusCommand implements CommandInterface {
    private double value;

    public MinusCommand(double value) {
        this.value = value;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return value * (-1);
    }
}
