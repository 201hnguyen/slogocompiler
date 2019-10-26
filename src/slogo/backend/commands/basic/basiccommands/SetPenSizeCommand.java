package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Movement;
import slogo.backend.utils.PenStatus;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class SetPenSizeCommand implements BasicCommandInterface {
    private static final double ACCURACY = 0.001;

    private TurtleHistory turtleHistory;

    public SetPenSizeCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());

        PenStatus initialPenStatus = turtle.getPenStatus();
        int index = (int) (parameters.get(0) + ACCURACY);
        PenStatus newPenStatus = new PenStatus(initialPenStatus.isPenDown(), index, initialPenStatus.getPenColor());
        turtleHistory.updateTurtle(turtleID, new Movement(curPos, curPos,turtle.getOrientation()), turtle.getDrawStatus(), newPenStatus);

        return index;
    }
}
