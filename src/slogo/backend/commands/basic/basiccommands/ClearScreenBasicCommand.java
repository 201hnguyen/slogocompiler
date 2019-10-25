package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Movement;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class ClearScreenBasicCommand implements BasicCommandInterface {

    private static final double INITIAL_ORIENTATION = 90;

    private TurtleHistory turtleHistory;

    public ClearScreenBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        double returnValue = curPos.distance(0, 0);

        Movement movement = new Movement(curPos, new Point2D(0, 0), INITIAL_ORIENTATION);

        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus());
        turtleHistory.initialize();

        return returnValue;
    }
}
