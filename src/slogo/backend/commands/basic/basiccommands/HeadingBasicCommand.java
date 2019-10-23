package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleManager;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class HeadingBasicCommand implements BasicCommandInterface {

    private TurtleHistory turtleHistory;

    public HeadingBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        return turtle.getOrientation();
    }
}
