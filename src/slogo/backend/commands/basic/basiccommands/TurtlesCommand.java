package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.TurtleHistory;

import java.util.List;

public class TurtlesCommand implements BasicCommandInterface {
    private TurtleHistory turtleHistory;

    public TurtlesCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        return turtleHistory.getActiveTurtles().size();
    }
}
