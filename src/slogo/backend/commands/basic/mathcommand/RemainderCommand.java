package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class RemainderCommand implements CommandInterface {
    private double parameter1;
    private double parameter2;

    public RemainderCommand(double parameter1, double parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return parameter1 - (int) (parameter1 / parameter2) * parameter2 ;
    }
}
