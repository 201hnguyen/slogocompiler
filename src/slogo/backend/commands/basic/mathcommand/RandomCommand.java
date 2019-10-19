package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class RandomCommand implements CommandInterface {

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        return ((int) Math.random() * parameters.get(0));
    }
}
