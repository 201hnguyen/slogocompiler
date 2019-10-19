package slogo.backend.commands.basic.querycommand;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class XCoordinateBasicCommand implements BasicCommandInterface {
    private TurtleManager turtleManager;

    public XCoordinateBasicCommand(TurtleManager turtleMangager) {
        this.turtleManager = turtleMangager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        return turtle.getXPos();
    }
}
