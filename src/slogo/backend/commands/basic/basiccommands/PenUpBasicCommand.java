package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.backend.utils.Movement;
import slogo.backend.utils.PenStatus;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class PenUpBasicCommand implements BasicCommandInterface {

    private TurtleHistory turtleHistory;

    public PenUpBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Point2D curPos = new Point2D(turtle.getXPos(), turtle.getYPos());
        Movement movement = new Movement(curPos, curPos, turtle.getOrientation());

        PenStatus initialPenStatus = turtle.getPenStatus();
        PenStatus newPenStatus = new PenStatus(false, initialPenStatus.getPenSize(), initialPenStatus.getPenColor());

        newPenStatus.compareAndSetChanged(initialPenStatus);

        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), newPenStatus);

        return 0;
    }
}
