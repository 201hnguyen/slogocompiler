package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class RandomCommand implements CommandInterface {
    private double value;

    public RandomCommand(double value) {
        this.value = value;
    }


    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return ((int) Math.random() * value);
    }
}
