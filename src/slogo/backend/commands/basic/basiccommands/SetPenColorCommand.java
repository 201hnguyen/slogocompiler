package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.*;

import java.util.List;

public class SetPenColorCommand implements BasicCommandInterface {
    private static final double ACCURACY = 0.001;

    private TurtleHistory turtleHistory;

    public SetPenColorCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos,turtle.getOrientation());

        PenStatus initialPenStatus = turtle.getPenStatus();
        int index = (int) (parameters.get(0) + ACCURACY);
        PenStatus newPenStatus = new PenStatus(initialPenStatus.isPenDown(), initialPenStatus.getPenSize(), index);
        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), newPenStatus);

        return index;
    }
}
