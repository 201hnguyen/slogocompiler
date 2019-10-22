package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class HeadingBasicCommand implements BasicCommandInterface {

    private TurtleManager turtleManager;

    public HeadingBasicCommand(TurtleManager turtleMangager) {
        this.turtleManager = turtleMangager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        return turtle.getOrientation();
    }
}
