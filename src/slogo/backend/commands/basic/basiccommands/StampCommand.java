package slogo.backend.commands.basic.basiccommands;

import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;
import java.util.List;

public class StampCommand implements BasicCommandInterface {
    @Override
    public double getReturnValue(TurtleHistory turtleHistory, List<Double> parameters, int turtleID) {
        TurtleModel turtleModel = turtleHistory.getTurtleModel(turtleID);
        turtleHistory.addOrClearStamps(false, turtleID);

        return turtleModel.getDrawStatus().getImageNum();
    }
}
