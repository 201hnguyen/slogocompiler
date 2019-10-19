package slogo.backend.commands.basic.querycommand;

import slogo.backend.commands.basic.CommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleManager;

import java.util.List;

public class IsShowingCommand implements CommandInterface {
    private TurtleManager turtleManager;

    public IsShowingCommand(TurtleManager turtleMangager) {
        this.turtleManager = turtleMangager;
    }

    @Override
    public double getReturnValue(List<Double> parameters, String turtleID) {
        Turtle turtle = turtleManager.getTurtle(turtleID);
        return turtle.isShowing()? 1d : 0d;
    }
}
