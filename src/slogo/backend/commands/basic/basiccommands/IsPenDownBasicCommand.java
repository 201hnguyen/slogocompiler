package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Turtle;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleManager;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class IsPenDownBasicCommand implements BasicCommandInterface {
    private TurtleHistory turtleHistory;

    public IsPenDownBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        return turtle.isPenDown()? 1d : 0d;
    }
}
