package slogo.backend.commands.basic.mathcommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.TurtleManager;

public class PiCommand implements CommandInterface {

    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        return Math.PI;
    }
}
