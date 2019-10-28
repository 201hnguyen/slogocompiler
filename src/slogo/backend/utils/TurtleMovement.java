package slogo.backend.utils;

import slogo.util.DrawStatus;
import slogo.util.Movement;
import slogo.util.PenStatus;

public class TurtleMovement {

    private int turtleID;
    private Movement movement;
    private DrawStatus drawStatus;
    private PenStatus penStatus;

    public TurtleMovement(int turtleID, Movement movement, DrawStatus drawStatus, PenStatus penStatus) {
        this.turtleID = turtleID;
        this.movement = movement;
        this.drawStatus = drawStatus;
        this.penStatus = penStatus;
        System.out.println(penStatus.isPenDown() + ", " + drawStatus.isVisibleChanged() + "movement");
    }

    public int getTurtleID() {
        return turtleID;
    }

    public Movement getMovement() {
        return movement;
    }

    public DrawStatus getDrawStatus() {
        return drawStatus;
    }

    public PenStatus getPenStatus() {
        return penStatus;
    }
}
