package slogo.backend.commands.basic.querycommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class XCoordinateCommand implements CommandInterface {
    @Override
    public double getReturnValue(TurtleManager turtleManager, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        return turtle.getXPos();
    }
}
