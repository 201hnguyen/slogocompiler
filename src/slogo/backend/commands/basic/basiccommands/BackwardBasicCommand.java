package slogo.backend.commands.basic.basiccommands;

import javafx.geometry.Point2D;
import slogo.backend.commands.basic.BasicCommandInterface;
import slogo.util.Movement;
import slogo.backend.utils.TurtleHistory;
import slogo.backend.utils.TurtleModel;

import java.util.List;

public class BackwardBasicCommand implements BasicCommandInterface {


    private TurtleHistory turtleHistory;

    public BackwardBasicCommand(TurtleHistory turtleHistory) {
        this.turtleHistory = turtleHistory;
    }

    @Override
    public double getReturnValue(List<Double> parameters, int turtleID) {
        TurtleModel turtle = turtleHistory.getTurtleModel(turtleID);
        Movement movement = new Movement(new Point2D(turtle.getXPos(), turtle.getYPos()),
                turtle.getOrientation(), (-1) * parameters.get(0));

        turtleHistory.updateTurtle(turtleID, movement, turtle.getDrawStatus(), turtle.getPenStatus());

        return parameters.get(0);
    }
}
